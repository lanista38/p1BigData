package ex5;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;


public class TUserMessageReducer extends Reducer<Text, Text, Text, Text> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context)
            throws IOException, InterruptedException {

        int count = 0;
        String all_msg = " ";

        //loop through messages and count them
        for (Text value : values){
            all_msg = all_msg.concat(value.toString()) + " ";
            count = count + 1;
            System.out.println(count);
        }

        String str_count = Integer.toString(count);
        all_msg = all_msg.concat("" + str_count);

        context.write(key, new Text(all_msg));
    }
}
