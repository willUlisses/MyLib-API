package br.com.will.MyLibraryApi.Model.Enumerations;

import lombok.Getter;

@Getter
public enum CopyStatus {

    AVAILABLE("available"),
    BORROWED("borrowed");

    private final String status;

    CopyStatus(String status) {
        this.status = status;
    }

}
