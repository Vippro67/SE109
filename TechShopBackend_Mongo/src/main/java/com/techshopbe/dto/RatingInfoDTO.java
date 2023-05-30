package com.techshopbe.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// chi tiet hoa don
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingInfoDTO {
	private float productRate;
	private int totalReviews;

}
