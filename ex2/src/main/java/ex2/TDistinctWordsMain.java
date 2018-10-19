package ex2;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * Created by dabuddahn on 03-28-17.
 */
public class TDistinctWordsMain {
    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.println("Wrong Args");
            System.exit(-1);
        }
        Job job = new Job();
        job.setJarByClass(ex2.TDistinctWordsMain.class);
        job.setJobName("Count TweetsbyUsr");

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.setMapperClass(ex2.TDistinctWordsMapper.class);
        job.setReducerClass(ex2.TDistinctWordsReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
