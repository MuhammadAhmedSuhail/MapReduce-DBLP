import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;

class Map extends Mapper<LongWritable, Text, Text, IntWritable> {
    private static final Text authpr = new Text();
    
    private List<String> curr_auth = new ArrayList<String>();
    
    int itera = 0;
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        if(itera == 150000) {
        	return;
        }
    	String ln = value.toString();
    	
        Matcher authmatch = Pattern.compile("<author>(.*?)</author>").matcher(ln);
        while (authmatch.find()) {
            String auth = authmatch.group(1).trim();
            curr_auth.add(auth);
        }

        if (ln.contains("</article>")) {
            for (int i = 0; i < curr_auth.size(); i++) {
                for (int j = i + 1; j < curr_auth.size(); j++) {
                    String auth1 = curr_auth.get(i);
                    String auth2 = curr_auth.get(j);
                    authpr.set(auth1 + ", " + auth2);
                    context.write(authpr, new IntWritable(1));
                }
            }
            curr_auth.clear();
        }
        itera = itera + 1;
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

public class Q2_20I0613 extends Configured implements Tool {
    
    public int run(String[] args) throws Exception {
    	
        Configuration conf = getConf();
        Job job = Job.getInstance(conf, "CoAuthorsGraph");
        job.setJarByClass(Q2_20I0613.class);
        job.setMapperClass(Map.class);
        job.setReducerClass(Reduce.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        return job.waitForCompletion(true) ? 0 : 1;
    }
    
    public static void main(String[] args) throws Exception {
        int res = ToolRunner.run(new Configuration(), new Q2_20I0613(), args);
        System.exit(res);
    }
}