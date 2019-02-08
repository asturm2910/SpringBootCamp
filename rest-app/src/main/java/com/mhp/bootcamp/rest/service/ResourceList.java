package com.mhp.bootcamp.rest.service;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor

public class ResourceList<DTO extends Object> extends ResourceSupport {

    private List<DTO> data;
    private List<Link> links = new ArrayList<>();

    public ResourceList(List<DTO> data)  {
        this.data = data;
    }

    public void add(Link link) {
        links.add(link);
    }

}
