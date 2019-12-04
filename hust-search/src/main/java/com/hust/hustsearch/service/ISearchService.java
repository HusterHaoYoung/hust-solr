package com.hust.hustsearch.service;

import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;

public interface ISearchService {
    String initAllData() throws IOException, SolrServerException;

}
