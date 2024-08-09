package com.rest.RestService.repository.impl;

import com.rest.RestService.Enum.Node;
import com.rest.RestService.model.DataNode;
import com.rest.RestService.model.Request;
import com.rest.RestService.repository.IDataNodeRepository;
import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Component
public class DataNodeRespository implements org.springframework.data.jpa.repository.JpaRepository<DataNode, > {

    List<DataNode> nodes;

    @SneakyThrows
    @PostConstruct
//    public void init(){
//        nodes = new HashSet<>();
//        nodes.add(DataNode.builder()
//                .build());


    public List<DataNode> getDummyDataNodes() throws URISyntaxException {

        // Create HttpHeaders for the Request
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        // Create a parent node
        DataNode parent1 = DataNode.builder()
                .id(UUID.randomUUID().toString())
                .name("Parent Node 1")
                .type(Node.NODE)
                .build();

        DataNode parent2 = DataNode.builder()
                .id(UUID.randomUUID().toString())
                .name("Parent Node 2")
                .type(Node.NODE)
                .build();

        // Create child nodes with parent nodes
        DataNode child1 = DataNode.builder()
                .id(UUID.randomUUID().toString())
                .name("Child Node 1")
                .type(Node.REQ)
                .parent(List.of(parent1, parent2))
                .request(new Request(HttpMethod.GET, new URI("http://example.com/api/resource1"), headers, ""))
                .build();

        DataNode child2 = DataNode.builder()
                .id(UUID.randomUUID().toString())
                .name("Child Node 2")
                .type(Node.REQ)
                .parent(List.of(parent1))
                .request(new Request(HttpMethod.POST, new URI("http://example.com/api/resource2"), headers, "{\"key\": \"value\"}"))
                .build();

        nodes.add(parent1);
        nodes.add(parent2);
        nodes.add(child1);
        nodes.add(child2);

        return nodes;
    }
//    }

    /**
     *
     */
    @Override
    public void save() {

    }

    /**
     *
     */
    @Override
    public void getAll() {

    }

    /**
     * @param id
     */
    @Override
    public void getByID(String id) {

    }

    /**
     * @param id
     */
    @Override
    public void delete(String id) {

    }

    /**
     * @param id
     */
    @Override
    public void update(String id) {

    }
}
