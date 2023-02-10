package com.sohu.tool;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseInnerRequest<T> {
    private String uri;

    private Long advertiserId;

    Object req;

    TypeReference<T> typeReference;

    String serverAppId;

    String serverUrl;

    String moduleName;

    public BaseInnerRequest(String uri, Object req, TypeReference<T> typeReference, String serverAppId, String serverUrl, String moduleName) {
        if (uri == null || req == null || typeReference == null
            || serverAppId == null || serverUrl == null || moduleName == null) {
            throw new IllegalArgumentException("uri, advertiserId, req, typeReference, serverAppId, serverUrl, moduleName can not be null");
        }

        this.uri = uri;
        this.req = req;
        this.typeReference = typeReference;
        this.serverAppId = serverAppId;
        this.serverUrl = serverUrl;
        this.moduleName = moduleName;
    }
}
