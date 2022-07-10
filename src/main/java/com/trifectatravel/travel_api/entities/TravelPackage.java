package com.trifectatravel.travel_api.entities;


import javax.persistence.*;

@Entity
@Table(name = "travel_package")
public class TravelPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;

    @Column(name = "trip_name")
    private String tripName;
    private String description;
    @Column(name = "image_file_path")
    private String imageFilePath;

    public TravelPackage(){

    }

    public TravelPackage(final String tripName, final String description, final String imageFilePath) {
        this.tripName = tripName;
        this.description = description;
        this.imageFilePath = imageFilePath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageFilePath() {
        return imageFilePath;
    }

    public void setImageFilePath(String imageFilePath) {
        this.imageFilePath = imageFilePath;
    }
}
