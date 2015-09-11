package baidu;

public class Ant {
    /*
     * step 表示蚂蚁每一个单位时间所走的长度
     */
    private final static int step = 1;

    /*
     * position表示蚂蚁所处的初始位置
     */
    private int position;

    /*
     * direction表示蚂蚁的前进方向，如果为1表示向27厘米的方向走， 如果为－1，则表示往0的方向走。
     */
    private int direction = 1;

    /*
     * 此函数运行一次，表示蚂蚁前进一个单位时间，如果已经走下木杆则会抛出异常
     */
    public void walk() {
        if (isOut()) {
            throw new RuntimeException("the ant is out");
        }
        position = position + this.direction * step;
    }

    ;


    /**
     * 检查蚂蚁是否已经走出木杆，如果走出返回true
     */

    public boolean isOut() {
        return position <= 0 || position >= 27;
    }

    /**
     * 检查此蚂蚁是否已经遇到另外一只蚂蚁
     *
     * @param ant
     * @return 如果遇到返回true
     */
    public boolean isEncounter(Ant ant) {
        return ant.position == this.position;
    }

    /**
     * 改变蚂蚁的前进方向
     */
    public void changeDistation() {
        direction = -1 * direction;
    }


    /**
     * 构造函数,设置蚂蚁的初始前进方向,和初始位置
     *
     * @param position
     * @param direction
     */
    public Ant(int position, int direction) {
        this.position = position;
        if (direction != 1) {
            this.direction = -1;//方向设置初始位置,比如为0时,也将其设置为1.这样可以方便后面的处理
        } else {
            this.direction = 1;
        }
    }

}