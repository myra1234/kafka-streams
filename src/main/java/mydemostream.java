import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import java.util.Properties;

public class mydemostream {

    public static void main(String[] args) {
        Properties prop = new Properties();
        prop.setProperty(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        prop.setProperty(StreamsConfig.APPLICATION_ID_CONFIG, "MyDemoStream");
        prop.setProperty(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.StringSerde.class.getName());
        prop.setProperty(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.StringSerde.class.getName());

        StreamsBuilder sb = new StreamsBuilder();

        KStream<String, String> ks = sb.stream("weekday");
        KStream<String,String> filteredks = ks.filter((k,v)-> v.contains("Monday"));

        filteredks.to("weekday-monday");

        KafkaStreams kafkaStreams = new KafkaStreams(sb.build(),prop);
        kafkaStreams.start();
    }
}
