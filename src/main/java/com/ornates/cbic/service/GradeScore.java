package com.ornates.cbic.service;

public class GradeScore {

	// grade score for GST 1A 
	public int marks1a(double total) {
		int rank=0;
		if(total>=80) {
			rank=10;
		}else if(total >= 75 && total < 80) {
			rank=7;
		}else if(total >= 70 && total < 75) {
			rank=4;
		}else {
			rank=0;
		}
		return rank;
	}

	// grade score for GST 1B 
	public int marks1b(double total) {
		int rank=0;
		if(total>10) {
			rank=0;
		}else if(total >= 6 && total <= 10) {
			rank=4;
		}else if(total >= 1 && total <= 5) {
			rank=7;
		}else {
			rank=10;
		}
		return rank;
	}
	// grade score for GST 1C
	public int marks1c(double total) {
		int rank=0;
		if(total>10) {
			rank=0;
		}else if(total >= 6 && total < 10) {
			rank=4;
		}else if(total >= 1 && total < 6) {
			rank=7;
		}else {
			rank=10;
		}
		return rank;
	}

	// grade score for GST 1D
	public int marks1d(double total) {
		int rank=0;
		if(total<=20) {
			rank=10;
		}else if(total >= 21 && total <= 30) {
			rank=7;
		}else if(total >= 31 && total <= 40) {
			rank=4;
		}else {
			rank=02;
		}
		return rank;
	}

	//grade score for Gst1E
	public int marks1e(double total) {
		int rank=0;
		if(total<=30) {
			rank=10;
		}else if(total >= 31 && total <= 40) {
			rank=7;
		}else if(total >= 41 && total <= 50) {
			rank=4;
		}else {
			rank=02;
		}
		return rank;
	}

	// rade score for Gst1F
	public int marks1f(double total) {
		int rank =0;
		if(total<=30) {
			rank=10;
		}else if(total >= 31 && total <= 40) {
			rank=7;
		}else if(total >= 41 && total <= 50) {
			rank=4;
		}else {
			rank=02;
		}
		return rank;
	}


	// grade score for GST 2 
	public int marks2(double total) {
		int rank=0;
		if(total<=5) {
			rank=10;
		}else if(total > 5 && total <= 10) {
			rank=7;
		}else if(total > 10 && total <= 15) {
			rank=4;
		}else {
			rank=2;
		}
		return rank;
	}
	// grade score for GST 3A 
	public int marks3a(double total) {
		int rank=0;
		if(total>=90) {
			rank=10;
		}else if(total >= 80 && total < 90) {
			rank=7;
		}else if(total >= 70 && total < 80) {
			rank=4;
		}else {
			rank=2;
		}
		return rank;
	}

	// grade score for Gst3B
	public int marks3b(double total) {
		int rank=0;
		if(total>=50) {
			rank=10;
		}else if(total >= 40 && total <= 49) {
			rank=7;
		}else if(total >= 30 && total <= 39) {
			rank=4;
		}else {
			rank=2;
		}
		return rank;
	}

	// grade score for GST 4A 
	public int marks4a(double total) {
		int rank=0;
		if(total>=30) {
			rank=10;
		}else if(total >= 25 && total <= 29) {
			rank=7;
		}else if(total >= 20 && total <= 24) {
			rank=4;
		}else {
			rank=2;
		}
		return rank;
	}

	// grade score for Gst 4B
	public int marks4b(double total) {
		int rank=0;
		if(total<=20) {
			rank=10;
		}else if(total >= 21 && total <= 30) {
			rank=7;
		}else if(total >= 31 && total <= 40) {
			rank=4;
		}else {
			rank=2;
		}
		return rank;
	}

	// grade score for GST 4C 
	public int marks4c(double total) {
		int rank=0;
		if(total>=20) {
			rank=10;
		}else if(total >= 19 && total <= 15) {
			rank=7;
		}else if(total >= 14 && total <= 10) {
			rank=4;
		}else {
			rank=2;
		}
		return rank;
	}

	//Grade score for Gst4D
	public int marks4d(double total) {
		int rank=0;
		if(total>=30) {
			rank=10;
		}else if(total >= 29 && total <= 20) {
			rank=7;
		}else if(total >= 19 && total <= 10) {
			rank=4;
		}else {
			rank=2;
		}
		return rank;
	}


	//Grade score for Gst5A
	public int marks5a(double total) {
		int rank=0;
		if(total>=30) {
			rank=10;
		}else if(total >= 20 && total < 30) {
			rank=7;
		}else if(total >= 10 && total < 20) {
			rank=4;
		}else {
			rank=2;
		}
		return rank;
	}
	//Grsde score for Gst5B
	public int marks5b(double total) {
		int rank=0;
		if(total<=20) {
			rank=10;
		}else if(total > 20 && total <= 30) {
			rank=7;
		}else if(total > 30 && total <= 40) {
			rank=4;
		}else {
			rank=2;
		}
		return rank;
	}
	//Grade Score for Gst6A
	public int marks6a(double total) {
		int rank=0;
		if(total>=40) {
			rank=10;
		}else if(total >= 30 && total <= 39) {
			rank=7;
		}else if(total >= 20 && total <= 29) {
			rank=4;
		}else {
			rank=2;
		}
		return rank;
	}
	// Grade Score for Gst6B
	public int marks6b(double total) {
		int rank=0;
		if(total<=10) {
			rank=10;
		}else if(total >= 11 && total <= 20) {
			rank=7;
		}else if(total >= 21 && total <= 30) {
			rank=4;
		}else {
			rank=2;
		}
		return rank;
	}
	// Grade Score for Gst6C
	public int marks6c(double total) {
		int rank=0;
		if(total>=40) {
			rank=10;
		}else if(total >= 30 && total <= 39) {
			rank=7;
		}else if(total >= 20 && total <= 29) {
			rank=4;
		}else {
			rank=2;
		}
		return rank;
	}

	//Grade Score for Gst6D	
	public int marks6d(double total) {
		int rank=0;
		if(total<=10) {
			rank=10;
		}else if(total > 10 && total <= 20) {
			rank=7;
		}else if(total > 20 && total <= 30) {
			rank=4;
		}else {
			rank=2;
		}
		return rank;
	}
	//Grade Score for Gst7
	public int marks7(double total) {
		int rank=0;
		if(total<=0) {
			rank=10;
		}else if(total >0 && total <= 5) {
			rank=7;
		}else if(total >5 && total <= 10) {
			rank=4;
		}else {
			rank=0;
		}
		return rank;
	}
	// Api Gst8A
	public int marks8a(double total) {
		int rank=0;
		if(total>=80) {
			rank=10;
		}else if(total >= 70 && total <= 79) {
			rank=7;
		}else if(total >= 60 && total <= 69) {
			rank=4;
		}else {
			rank=2;
		}
		return rank;
	}

	//Grade sScore for Gst8B
	public int marks8b(double total) {
		int rank=0;
		if(total<=10) {
			rank=10;
		}else if(total >= 11 && total <= 20) {
			rank=7;
		}else if(total >= 21 && total <= 30) {
			rank=4;
		}else {
			rank=2;
		}
		return rank;
	}

	// Api Gst9A
	public int marks9a(double total) {
		int rank=0;
		if(total<=0) {
			rank=10;
		}else if(total >= 1 && total <= 5) {
			rank=7;
		}else if(total >= 6 && total <= 10) {
			rank=4;
		}else {
			rank=2;
		}
		return rank;
	}
	// Api Gst9B
	public int marks9b(double total) {
		int rank=0;
		if(total >= 40) {
			rank=10;
		}else if(total >= 30 && total <= 39) {
			rank=7;
		}else if(total >= 20 && total <= 29) {
			rank=4;
		}else {
			rank=2;
		}
		return rank;
	}

	// grade score for GST 10A
	public int marks10a ( double total){
		int rank = 0;
		if (total >= 80) {
			rank = 10;
		} else if (total >= 70 && total < 80) {
			rank = 7;
		} else if (total >= 60 && total < 70) {
			rank = 4;
		} else {
			rank = 2;
		}
		return rank;
	}

	// Grade score for GST 10B
	public int marks10b ( double total){
		int rank = 0;
		if (total <= 20) {
			rank = 10;
		} else if (total > 20 && total <= 30) {
			rank = 7;
		} else if (total > 30 && total <= 40) {
			rank = 4;
		} else {
			rank = 2;
		}
		return rank;
	}

	// grade score for GST 10C
	public int marks10c ( double ratio){
		int rank = 0;
		if (ratio >= 40) {
			rank = 10;
		} else if (ratio >= 30 && ratio < 40) {
			rank = 7;
		} else if (ratio >= 20 && ratio < 30) {
			rank = 4;
		} else {
			rank = 2;
		}
		return rank;
	}

	// grade score for GST 11A
	public int marks11a ( double total, double dis){
		int rank = 0;
		if ((total >= 40) || (dis > 75)) {
			rank = 10;
		} else if ((total >= 35 && total < 40) || (dis > 70 && dis <= 75)) {
			rank = 7;
		} else if ((total >= 30 && total < 35) || (dis > 65 && dis <= 70)) {
			rank = 4;
		} else {
			rank = 2;
		}
		return rank;
	}

	// Service Layer for grading GST 11B
	public int marks11b ( double total){
		int rank = 0;
		if (total <= 10) {
			rank = 10;
		} else if (total >= 11 && total <= 20) {
			rank = 7;
		} else if (total >= 21 && total <= 30) {
			rank = 4;
		} else {
			rank = 2;
		}
		return rank;
	}

	// grade score for GST 11c
	public int marks11c ( double total, double dis){
		int rank = 0;
		if ((total >= 40) || (dis > 75)) {
			rank = 10;
		} else if ((total >= 35 && total < 40) || (dis > 70 && dis <= 75)) {
			rank = 7;
		} else if ((total >= 30 && total < 35) || (dis > 65 && dis <= 70)) {
			rank = 4;
		} else {
			rank = 2;
		}
		return rank;
	}

	// grade score for GST 11D
	public int marks11d ( double total){
		int rank = 0;
		if (total <= 10) {
			rank = 10;
		} else if (total >= 11 && total <= 20) {
			rank = 7;
		} else if (total >= 21 && total <= 30) {
			rank = 4;
		} else {
			rank = 2;
		}
		return rank;
	}




}