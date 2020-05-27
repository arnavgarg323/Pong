import java.awt.Color;
import java.awt.Graphics;

public class Paddle {

    private int paddleNumber;
    private int x;
    private int y;
    private int width = 25;
    private int height = 250;
    private int score;

    public Paddle(Pong pong, int paddleNumber) {

        this.paddleNumber = paddleNumber;

        if (paddleNumber == 1) {

            this.x = 0;
        }

        if (paddleNumber == 2) {

            this.x = pong.getWidth() - width;
        }

        this.y = pong.getHeight() / 2 - this.height / 2;
    }

    public void render(Graphics g) {

        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, height);
    }

    public void move(boolean up) {

        int speed = 15;

        if (up) {

            if (y - speed > 0) {

                y -= speed;

            } else {

                y = 0;
            }
        } else {

            if (y + height + speed < Pong.getPong().getHeight()) {

                y += speed;

            } else {

                y = Pong.getPong().getHeight() - height;
            }
        }
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getPaddleNumber() {
        return paddleNumber;
    }
}