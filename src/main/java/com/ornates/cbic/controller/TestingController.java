//while (rsGst14aa.next()) {
//String zoneName = rsGst14aa.getString("ZONE_NAME");
//zone_code = rsGst14aa.getString("ZONE_CODE");
//
//double total3a = rsGst14aa.getDouble("score_of_subpara3a");
//double total3b = rsGst14aa.getDouble("score_of_parameter3b");
//
//double median3a = rsGst14aa.getDouble("median_numerator_3a");
//double median3b = rsGst14aa.getDouble("median_numerator_3b");
//
//Double numerator_3a = rsGst14aa.getDouble("numerator_3a");
//Double numerator_3b = rsGst14aa.getDouble("numerator_3b");
//
//int way_to_grade3a = score.marks3a(total3a);
//int way_to_grade3b = score.marks3b(total3b);
//
//int insentavization3a = way_to_grade3a;
//int insentavization3b = way_to_grade3b;
//
//// Logic to adjust insentavization3a and insentavization3b based on the median and numerator values
//					if (numerator_3a > median3a && way_to_grade3a < 10) {
//insentavization3a += 1;
//        }
//        if (numerator_3b > median3b && way_to_grade3b < 10) {
//insentavization3b += 1;
//        }
//
//Integer way_to_grade = way_to_grade3a + way_to_grade3b;
//Integer insentavization = insentavization3a + insentavization3b;
//
//double sub_parameter_weighted_average3a = insentavization3a * 0.5;
//double sub_parameter_weighted_average3b = insentavization3b * 0.5;
//
//double total_weighted_average = sub_parameter_weighted_average3a + sub_parameter_weighted_average3b;
//double sub_parameter_weighted_average = 0.00;
//Integer Zonal_rank = rsGst14aa.getInt("z_rank");
//String commName = "ALL";
//String gst = "ALL";
//String absval = "null";
//String ra = "SCRUTINY/ASSESSMENT";
//
//totalScore = new TotalScore(zoneName, commName, zone_code, total_weighted_average, absval, Zonal_rank, gst, ra, way_to_grade, insentavization, sub_parameter_weighted_average);
//					allGstaList.add(totalScore);
//
//
//
//					System.out.println(zoneName);
//					System.out.println("total3a: " + total3a);
//					System.out.println("numerator_3a : " + numerator_3a);
//					System.out.println("median3a : " + median3a);
//					System.out.println("way_to_grade3a: " + way_to_grade3a);
//					System.out.println("insentavization3a : " + insentavization3a);
//					System.out.println("sub_parameter_weighted_average3a : " + sub_parameter_weighted_average3a);
//
//					System.out.println("total3b: " + total3b);
//					System.out.println("numerator_3b : " + numerator_3b);
//					System.out.println("median3b : " + median3b);
//					System.out.println("way_to_grade3b: " + way_to_grade3b);
//					System.out.println("insentavization3b : " + insentavization3b);
//					System.out.println("sub_parameter_weighted_average3b : " + sub_parameter_weighted_average3b);
//					System.out.println("total_score : " + total_weighted_average);
//					System.out.println("********************************************************************************************");
//				}