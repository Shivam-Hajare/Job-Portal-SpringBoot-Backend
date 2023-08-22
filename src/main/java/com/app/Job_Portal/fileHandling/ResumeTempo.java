package com.app.Job_Portal.fileHandling;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name="resume_tempo")
@Setter
@Getter
@NoArgsConstructor
public class ResumeTempo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "resume")
    byte[] res;

    String name;

    public ResumeTempo(Long id) {
        this.id = id;
    }
}
