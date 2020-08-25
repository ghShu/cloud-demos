package com.ads;

import com.google.api.services.bigquery.model.TableRow;
import com.google.cloud.bigtable.dataflow.CloudBigtableIO;
import com.google.cloud.bigtable.dataflow.CloudBigtableScanConfiguration;
import com.google.cloud.dataflow.sdk.Pipeline;
import com.google.cloud.dataflow.sdk.io.Read;
import com.google.cloud.dataflow.sdk.options.PipelineOptions;
import com.google.cloud.dataflow.sdk.options.PipelineOptionsFactory;
import com.google.cloud.dataflow.sdk.transforms.DoFn;
import com.google.cloud.dataflow.sdk.transforms.ParDo;
import com.google.cloud.dataflow.sdk.values.PCollection;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class PostDumpFlow {

   private static final String PROJECT_ID = "xxxx";
   private static final String INSTANCE_ID = "around-ad"
   private static final String TABLE_ID = "ad"
   private static final Charset UTF8_CHARSET = Charset.forName("UTF-8");


   public static void main(String[] args) {
       // Start by defining the options for the pipeline.
       PipelineOptions options = PipelineOptionsFactory.fromArgs(args).create();

       Pipeline p = Pipeline.create(options);
      
       CloudBigtableScanConfiguration config = new CloudBigtableScanConfiguration.Builder()
               .withProjectId(PROJECT_ID)
               .withInstanceId(INSTANCE_ID)
               .withTableId(TABLE_ID)
               .build();

        PCollection<Result> btRows = p.apply(Read.from(CloudBigtableIO.read(config)));
        PCollection<TableRow> bqRows = btRows.apply(ParDo.of(new DoFn<Result, TableRow>() {
            @Override
            public void processElement(ProcessContext c) {
                Result result = c.element();
                String id = new String(result.getValue(Bytes.toBytes("features"), Bytes.toBytes("id")), UTF8_CHARSET);
                String description = new String(result.getValue(Bytes.toBytes("features"), Bytes.toBytes("description")), UTF8_CHARSET);
                String url = new String(result.getValue(Bytes.toBytes("features"), Bytes.toBytes("url")), UTF8_CHARSET);
                String image_url = new String(result.getValue(Bytes.toBytes("features"), Bytes.toBytes("image_url")), UTF8_CHARSET);
                Long view = ByteBuffer.wrap(result.getColumnLatestCell(Bytes.toBytes("interaction"), Bytes.toBytes("view")).getValueArray()).getLong();
                Long click = ByteBuffer.wrap(result.getColumnLatestCell(Bytes.toBytes("interaction"), Bytes.toBytes("click")).getValueArray()).getLong();

                TableRow row = new TableRow();
                row.set("id", id);
                row.set("url", url);
                row.set("description", description);
                row.set("image_url", image_url);
                row.set("view", view);
                row.set("click", click);

                c.output(row);
            }
        }));

        List<TableFieldSchema> fields = new ArrayList<>();
        fields.add(new TableFieldSchema().setName("description").setType("STRING"));
        fields.add(new TableFieldSchema().setName("id").setType("STRING"));
        fields.add(new TableFieldSchema().setName("image_url").setType("STRING"));
        fields.add(new TableFieldSchema().setName("url").setType("STRING"));
        fields.add(new TableFieldSchema().setName("view").setType("INTEGER"));
        fields.add(new TableFieldSchema().setName("click").setType("INTEGER"));

        TableSchema schema = new TableSchema().setFields(fields);
        bqRows.apply(BigQueryIO.Write
                .named("Write")
                .to(PROJECT_ID + ":" + "ad_analysis" + "." + "daily_dump_1")
                .withSchema(schema)
                .withWriteDisposition(BigQueryIO.Write.WriteDisposition.WRITE_TRUNCATE)
                .withCreateDisposition(BigQueryIO.Write.CreateDisposition.CREATE_IF_NEEDED));

       p.run();
   }
}

