//import com.ornates.cbic.service.RelevantAspect;while (rsGst14aa.next()) {
//String commname = "All";
//String ra = RelevantAspect.Gst3B_RA;
//String zoneName = rsGst14aa.getString("ZONE_NAME");
//String zoneCode = rsGst14aa.getString("ZONE_CODE");
//String absval = rsGst14aa.getString("absval");
//Double t_score = rsGst14aa.getDouble("score_of_parameter");
//median = rsGst14aa.getDouble("median_numerator_3b");
//Double numerator_3b = rsGst14aa.getDouble("numerator_3b");
//
//String formattedTotal = String.format("%.2f", t_score);
//double totalScore = Double.parseDouble(formattedTotal);
//int way_to_grade = score.marks3b(totalScore);
//int insentavization = score.marks3b(totalScore);
//                    System.out.println("insentavization3b :-" + insentavization);
//
//                    if (numerator_3b > median && way_to_grade < 10) {
//insentavization += 1;
//        }
//
//        System.out.println("insentavization3b after :-" + insentavization);
//
//int Zonal_rank = 0;
//String gst = "no";
//
//double sub_parameter_weighted_average = insentavization * 0.5 ;
////double sub_parameter_weighted_average = 0.00 ;
//
//gsta = new GST4A(zoneName, commname, totalScore, absval, zoneCode, ra, Zonal_rank, gst, way_to_grade, insentavization, sub_parameter_weighted_average);
//                    allGstaList.add(gsta);
//                }
//                        System.out.println("gst3b median zone wise:- " + median); //**************************** for testing ******************************************