//WITH ranked_data AS (
//        SELECT
//                SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY) AS numerator_3b,
//ROW_NUMBER() OVER (ORDER BY SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY)) AS row_num,
//COUNT(*) OVER () AS total_count
//FROM mis_gst_commcode AS cc
//RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE
//WHERE 14c.MM_YYYY <= '2023-05-01'
//GROUP BY cc.ZONE_CODE
//),
//median_data AS (
//        SELECT
//                CASE
//            WHEN total_count % 2 = 1 THEN
//                (SELECT numerator_3b FROM ranked_data WHERE row_num = (total_count + 1) / 2)
//ELSE
//        (SELECT AVG(numerator_3b) FROM ranked_data WHERE row_num IN ((total_count / 2), (total_count / 2) + 1))
//END AS median_numerator_3b
//FROM ranked_data
//LIMIT 1
//        )
//SELECT t.ZONE_NAME, t.ZONE_CODE, t.score_of_parameter3b,t.numerator_3b,
//DENSE_RANK() OVER (ORDER BY t.score_of_parameter3b DESC) AS z_rank,
//m.median_numerator_3b
//FROM (
//        SELECT zc.ZONE_NAME, cc.ZONE_CODE,
//        SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY) AS numerator_3b,
//        (SUM(14c.AMOUNT_RECOVERED_TAX + 14c.AMOUNT_RECOVERED_INTEREST + 14c.AMOUNT_RECOVERED_PENALTY) * 100) / SUM(14c.TAX_LIABILITY_DETECTECT) AS score_of_parameter3b
//FROM mis_gst_commcode AS cc
//RIGHT JOIN mis_dggst_gst_scr_1 AS 14c ON cc.COMM_CODE = 14c.COMM_CODE
//LEFT JOIN mis_gst_zonecode AS zc ON zc.ZONE_CODE = cc.ZONE_CODE
//WHERE 14c.MM_YYYY <= '2023-05-01'
//GROUP BY cc.ZONE_CODE, zc.ZONE_NAME
//) AS t
//CROSS JOIN median_data m
//ORDER BY t.score_of_parameter3b DESC;
