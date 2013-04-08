package storm.starter;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.testing.TestWordSpout;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;
import storm.starter.bolt.MergeObjects;
import storm.starter.bolt.RankObjects;
import storm.starter.bolt.RollingCountObjects;

public class RollingTopWords {
    public static void main(String[] args) throws Exception {

        final int TOP_N = 3;

        TopologyBuilder builder = new TopologyBuilder();

        builder.setSpout("1", new TestWordSpout(), 5);

        builder.setBolt("2", new RollingCountObjects(60, 10), 4)
                .fieldsGrouping("1", new Fields("word"));
        builder.setBolt("3", new RankObjects(TOP_N), 4)
                .fieldsGrouping("2", new Fields("obj"));
        builder.setBolt("4", new MergeObjects(TOP_N))
                .globalGrouping("3");
        Config conf = new Config();
        conf.setDebug(true);

        LocalCluster cluster = new LocalCluster(); // 本地模式启动集群
        cluster.submitTopology("rolling-demo", conf, builder.createTopology());
        Thread.sleep(10000);
        cluster.shutdown();
    }
}