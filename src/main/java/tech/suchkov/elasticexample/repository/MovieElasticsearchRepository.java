package tech.suchkov.elasticexample.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import tech.suchkov.elasticexample.entity.MovieDoc;

public interface MovieElasticsearchRepository extends ElasticsearchRepository<MovieDoc, Long> {
    @Query("""
            {
              "bool": {
                "should": [
                  {
                    "multi_match": {
                      "query": "?0",
                      "fields": ["movie^4", "overview^3"],
                      "type": "best_fields",
                      "operator": "or"
                    }
                  },
                  {
                    "match_phrase": {
                      "movie": {
                        "query": "?0",
                        "boost": 3
                      }
                    }
                  },
                  {
                    "match_phrase": {
                      "overview": {
                        "query": "?0",
                        "boost": 2
                      }
                    }
                  }
                ],
                "minimum_should_match": 1
              }
            }
            """)
    Page<MovieDoc> searchByQuery(String query, Pageable pageable);

}
