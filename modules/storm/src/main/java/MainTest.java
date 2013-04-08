import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.testing.TestWordSpout;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;
import backtype.storm.utils.Utils;

/**
 * Created with IntelliJ IDEA.
 * User: FANLU
 * Date: 13-4-8
 * Time: 下午3:00
 * To change this template use File | Settings | File Templates.
 */
public class MainTest {
    public static void main(String[] args){
        TopologyBuilder builder = new TopologyBuilder();

        builder.setSpout("1", new TestWordSpout(), 5);

        builder.setBolt("2", new SplitSentence(), 10).shuffleGrouping("1");

        builder.setBolt("3", new WordCount(), 20).fieldsGrouping("2", new Fields("word"));

        Config conf = new Config();

        conf.setDebug(true);

        conf.setNumWorkers(2);

        LocalCluster cluster = new LocalCluster();

        cluster.submitTopology("test", conf, builder.createTopology());

        Utils.sleep(10000);

        cluster.killTopology("test");

        cluster.shutdown();
    }
}
