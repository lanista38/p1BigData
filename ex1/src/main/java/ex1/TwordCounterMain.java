package ex1;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class TwordCounterMain {

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.println("Args wrong");
            System.exit(-1);
        }
        //Job Creation and Args setup
        Job job = new Job();
        job.setJarByClass(p1.TwordCounterMain.class);
        job.setJobName("Count TweetsbyUsr");
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        //Calls to mapper and reducer
        job.setMapperClass(p1.TwordCounterMapper.class);
        job.setReducerClass(p1.TwordCounterReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
