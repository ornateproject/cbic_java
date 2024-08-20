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

//
//import com.ornates.cbic.service.RelevantAspect;while(rsGst14aa.next()) {
//String commname = "All";
//String ra = RelevantAspect.Gst3A_RA;
//String zoneName = rsGst14aa.getString("ZONE_NAME");
//String zoneCode = rsGst14aa.getString("ZONE_CODE");
//String absval = rsGst14aa.getString("absval");
//Double t_score = rsGst14aa.getDouble("total_score");
//median = rsGst14aa.getDouble("median_numerator_3a");
//Double numerator_3a = rsGst14aa.getDouble("numerator_3a");
//
//String formattedTotal = String.format("%.2f", t_score);
//double totalScore = Double.parseDouble(formattedTotal);
//int way_to_grade = score.marks3a(totalScore);
//int insentavization = score.marks3a(totalScore);
//
//                    if (numerator_3a > median && way_to_grade < 10) {
//insentavization += 1;
//        }
//
//
//int Zonal_rank = 0;
//String gst = "no";
//
//Double sub_parameter_weighted_average = insentavization * 0.5 ;
//
//gsta = new GST4A(zoneName, commname, totalScore, absval, zoneCode, ra, Zonal_rank, gst, way_to_grade, insentavization, sub_parameter_weighted_average);
//                    allGstaList.add(gsta);
//                }
//                        System.out.println("gst3a median zone wise :- " + median); //********************************** for testing ************************************
