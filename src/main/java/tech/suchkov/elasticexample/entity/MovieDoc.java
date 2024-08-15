package tech.suchkov.elasticexample.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Setting;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "movies_index")
@Setting(settingPath = "/elasticsearch-settings.json")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDoc {
    @Id
    private Long id;

    @Field(type = FieldType.Text, analyzer = "russian_analyzer")
    private String movie;

    @Field(type = FieldType.Text, analyzer = "russian_analyzer")
    private String overview;
}
