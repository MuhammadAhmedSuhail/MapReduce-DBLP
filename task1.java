import java.io.IOException;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

class Map extends Mapper<LongWritable, Text, Text, IntWritable> {

  private final static IntWritable one = new IntWritable(1);
  private Text wrd = new Text();

  public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
    String ln = value.toString();
	String yr = "";

    if (ln.contains("<year>")) {    	
    	yr = ln.substring(ln.indexOf("<year>") + 6, ln.indexOf("</year>"));
    }

	wrd.set(yr);
	context.write(wrd, one);
  }
}

class Reduce extends Reducer<Text, IntWritable, Text, IntWritable> {

  private IntWritable rst = new IntWritable();

  public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
    int ct = 0;

    for (IntWritable val : values) {
      ct += val.get();
    }

    rst.set(ct);
    context.write(key, rst);
  }
}

public class Q1_20I0613 {
    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "Article Count");
        job.setJarByClass(Q1_20I0613.class);

        job.setInputFormatClass(TextInputFormat.class);
        TextInputFormat.addInputPath(job, new Path(args[0]));

        job.setMapperClass(Map.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setReducerClass(Reduce.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        job.setOutputFormatClass(TextOutputFormat.class);
        TextOutputFormat.setOutputPath(job, new Path(args[1]));

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }

}