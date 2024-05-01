package com.iapps.iappsxmlreader.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "epaper")
public class Epaper implements Serializable {

    private static final long serialVersionUID = -8951768357073878263L;

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "newspaper_name")
    private String newspaperName;
    private Long width;
    private Long height;
    private Long dpi;
    @Column(name = "uploaded_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date uploadedAt;
    @Column(name = "file_name")
    private String filename;

}
