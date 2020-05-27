import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Ball {

    private int x;
    private int y;
    private int width = 25;
    private int height = 25;
    private int motionX;
    private int motionY;
    private Random random;
    private Pong pong;
    private int amountOfHits;

    public Ball(Pong pong) {

        this.pong = pong;

        this.random = new Random();

        spawn();
    }

    public void update(Paddle paddle1, Paddle paddle2) {

        int speed = 5;

        this.x += motionX * speed;
        this.y += motionY * speed;

        if (this.y + height - motionY > pong.getHeight() || this.y + motionY < 0) {

            if (this.motionY < 0) {

                this.y = 0;
                this.motionY = random.nextInt(4);

                if (motionY == 0) {

                    motionY = 1;
                }
            } else {

                this.motionY = -random.nextInt(4);
                this.y = pong.getHeight() - height;

                if (motionY == 0) {

                    motionY = -1;
                }
            }
        }

        if (checkCollision(paddle1) == 1) {

            this.motionX = 1 + (amountOfHits / 5);
            this.motionY = -2 + random.nextInt(4);

            if (motionY == 0) {

                motionY = 1;
            }
            amountOfHits++;
        } else if (checkCollision(paddle2) == 1) {

            this.motionX = -1 - (amountOfHits / 5);
            this.motionY = -2 + random.nextInt(4);

            if (motionY == 0) {

                motionY = 1;
            }
            amountOfHits++;
        }

        if (checkCollision(paddle1) == 2) {

            paddle2.setScore(paddle2.getScore() + 1);
            spawn();
        } else if (checkCollision(paddle2) == 2) {

            paddle1.setScore(paddle1.getScore() + 1);
            spawn();
        }
    }

    public void spawn() {

        this.amountOfHits = 0;
        this.x = pong.getWidth() / 2 - this.width / 2;
        this.y = pong.getHeight() / 2 - this.height / 2;

        this.motionY = -2 + random.nextInt(4);

        if (motionY == 0) {
            
            motionY = 1;
        }

        if (random.nextBoolean()) {

            motionX = 1;
        } else {

            motionX = -1;
        }
    }

    public int checkCollision(Paddle paddle) {

        if (this.x < paddle.getX() + paddle.getWidth() && this.x + width > paddle.getX() && this.y < paddle.getY() + paddle.getHeight() && this.y + height > paddle.getY()) {

            return 1; //bounce
        }
        else if ((paddle.getX() > x && paddle.getPaddleNumber() == 1) || (paddle.getX() < x - width && paddle.getPaddleNumber() == 2)) {

            return 2; //score
        }
        return 0; //nothing
    }

    public void render(Graphics g) {

        g.setColor(Color.WHITE);
        g.fillOval(x, y, width, height);
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}