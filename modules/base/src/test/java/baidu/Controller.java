package baidu;

public class Controller {

    public static void main(String[] args) {

        int time = 0;
        for (int i = 0; i < 32; i++) {
            Ant[] antArray = getAntList(getPoistions(), getDirections(i));
            while (!isAllOut(antArray)) {
                for (Ant ant : antArray) {
                    if (!ant.isOut()) {
                        ant.walk();
                    }
                }
                time++;
                // 查看是否有已经相遇的Ant,如果有则更改其前进方向
                dealEncounter(antArray);
            }
            System.out.println(time);

            // 将时间归0,这样可以重新设置条件,再次得到全部走完所需要的时间.
            time = 0;
        }

    }

    /**
     * 这个函数的算法很乱，但暂时能解决问题
     *
     * @param list
     */
    public static void dealEncounter(Ant[] antArray) {

        int num_ant = antArray.length;
        for (int j = 0; j < num_ant; j++) {
            for (int k = j + 1; k < num_ant; k++) {
                if (antArray[j].isEncounter(antArray[k])) {
                    antArray[j].changeDistation();
                    antArray[k].changeDistation();
                }
            }
        }

    }

    /**
     * 因为有5只Ant，所以组合之后有32种组合.刚好用5位二进制来表示,如果为0则表示Ant往0的方向走 如果为1,则表示往27的方向走
     * <p/>
     * 注:在通过Ant的构造函数设置初始值时,通过过滤把0修改成了-1.
     */
    public static int[] getDirections(int seed) {
        int result[] = new int[5];
        result[0] = seed % 2;
        result[1] = seed / 2 % 2;
        result[2] = seed / 4 % 2;
        result[3] = seed / 8 % 2;
        result[4] = seed / 16 % 2;

        System.out.println("directions is " + result[0] + "|" + result[1] + "|"
                + result[2] + "|" + result[3] + "|" + result[4]);

        return result;

    }

    /**
     * 批量设置Ant的初始位置,这样设置不是十分必要,可以直接在代码中设置
     *
     * @return
     */
    public static int[] getPoistions() {
        return new int[]{3, 7, 11, 17, 23};
    }

    /**
     * 取得设置好初始值的5只Ant
     *
     * @param positions
     * @param directions
     * @return
     */
    public static Ant[] getAntList(int[] positions, int[] directions) {
        Ant ant3 = new Ant(positions[0], directions[0]);
        Ant ant7 = new Ant(positions[1], directions[1]);
        Ant ant11 = new Ant(positions[2], directions[2]);
        Ant ant17 = new Ant(positions[3], directions[3]);
        Ant ant23 = new Ant(positions[4], directions[4]);

        return new Ant[]{ant3, ant7, ant11, ant17, ant23};
    }

    /**
     * 判断是否所有的Ant都已经走出了木杆,也就是设置退出条件
     *
     * @param antArray
     * @return
     */
    public static boolean isAllOut(Ant[] antArray) {
        for (Ant ant : antArray) {
            if (ant.isOut() == false) {
                return false;
            }
        }
        return true;
    }
}