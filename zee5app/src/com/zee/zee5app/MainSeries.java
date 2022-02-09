package com.zee.zee5app;

import java.util.Optional;

import com.zee.zee5app.dto.Series;
import com.zee.zee5app.exception.IdNotFoundException;
import com.zee.zee5app.exception.InvalidIdLengthException;
import com.zee.zee5app.exception.LocationNotFoundException;
import com.zee.zee5app.exception.NameNotFoundException;
import com.zee.zee5app.service.SeriesService;
import com.zee.zee5app.service.impl.SeriesServiceImpl;

public class MainSeries {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Series series1 = null, series2 = null, series3 = null, series4 = null, series5 = null, series6 = null, series7 = null;
		
		try {
			series1 = new Series("00001", "TestSeries1", "TestSeriesLocation1", "TestSeriesDate1", "TestSeriesTrailer1", "TestSeriesLanguage1", null, "TestSeriesLength1");
		} catch (InvalidIdLengthException e) {
			// TODO Auto-generated catch block
			try {
				series1 = new Series("000001", "TestSeries1", "TestSeriesLocation1", "TestSeriesDate1", "TestSeriesTrailer1", "TestSeriesLanguage1", null, "TestSeriesLength1");
			} catch (InvalidIdLengthException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		try {
			series2 = new Series("000002", "TestSeries2", "TestSeriesLocation2", "TestSeriesDate2", "TestSeriesTrailer2", "TestSeriesLanguage2", null, "TestSeriesLength2");
			series3 = new Series("000003", "TestSeries3", "TestSeriesLocation3", "TestSeriesDate3", "TestSeriesTrailer3", "TestSeriesLanguage3", null, "TestSeriesLength3");
			series4 = new Series("000004", "TestSeries4", "TestSeriesLocation4", "TestSeriesDate4", "TestSeriesTrailer4", "TestSeriesLanguage4", null, "TestSeriesLength4");
			series5 = new Series("000005", "TestSeries5", "TestSeriesLocation5", "TestSeriesDate5", "TestSeriesTrailer5", "TestSeriesLanguage5", null, "TestSeriesLength5");
			series6 = new Series("000006", "TestSeries6", "TestSeriesLocation6", "TestSeriesDate6", "TestSeriesTrailer6", "TestSeriesLanguage6", null, "TestSeriesLength6");
			series7 = new Series("000007", "TestSeries7", "TestSeriesLocation7", "TestSeriesDate7", "TestSeriesTrailer7", "TestSeriesLanguage7", null, "TestSeriesLength7");
		} catch (InvalidIdLengthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(series1);
		System.out.println(series2);
		System.out.println(series3);
		System.out.println(series4);
		System.out.println(series5);
		System.out.println(series6);
		
		SeriesService seriesService = SeriesServiceImpl.getInstance();
		
		System.out.println("		***Adding series1, series2, series3, series4, series5 & series6.***");
		System.out.println(seriesService.addSeries(series1));
		System.out.println(seriesService.addSeries(series2));
		System.out.println(seriesService.addSeries(series3));
		System.out.println(seriesService.addSeries(series4));
		System.out.println(seriesService.addSeries(series5));
		System.out.println(seriesService.addSeries(series6));
		
		System.out.println("		***It is TreeSet with asecnding order compareTo.***");
		seriesService.getAllSeries().forEach(e->System.out.println(e));
		
		System.out.println("		***Trying to add series1 & series6 once more.***");
		System.out.println(seriesService.addSeries(series1));
		System.out.println(seriesService.addSeries(series6));
		
		System.out.println("		***Updating series6 with series7.***");
		try {
			System.out.println(seriesService.updateSeries("000006", series7));
		} catch (IdNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("		***Updating series6 with series7 again.***");
		try {
			System.out.println(seriesService.updateSeries("000006", series7));
		} catch (IdNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		seriesService.getAllSeries().forEach(e->System.out.println(e));
		
		System.out.println("		***Deleting series7 which is present.***");
		try {
			System.out.println(seriesService.deleteSeriesById("000007"));
		} catch (IdNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("		***Deleting series6 which is not present.***");
		try {
			System.out.println(seriesService.deleteSeriesById("000006"));
		} catch (IdNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		seriesService.getAllSeries().forEach(e->System.out.println(e));
		
		System.out.println("		***Getting series with id 000004(Present).***");
		Optional<Series> optionalSeries = null;
		try {
			optionalSeries = seriesService.getSeriesById("000004");
			System.out.println(optionalSeries.get());
		} catch (IdNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println("		***Getting series with id 000006(Not Present).***");
		try {
			optionalSeries = seriesService.getSeriesById("000006");
			System.out.println(optionalSeries.get());
		} catch (IdNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println("		***Getting series with Location TestSeriesLocation3(Present).***");
		Series series = null;
		try {
			series = seriesService.getSeriesByLocation("TestSeriesLocation3");
			System.out.println(series);
		} catch (LocationNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println("		***Getting series with Location TestSeriesLocation7(Not Present).***");
		try {
			series = seriesService.getSeriesByLocation("TestSeriesLocation7");
			System.out.println(series);
		} catch (LocationNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println("		***Getting series with Name TestSeries5(Present).***");
		try {
			optionalSeries = seriesService.getSeriesByName("TestSeries5");
			System.out.println(optionalSeries.get());
		} catch (NameNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println("		***Getting series with Name TestSeries6(Not Present).***");
		try {
			optionalSeries = seriesService.getSeriesByName("TestSeries6");
			System.out.println(optionalSeries.get());
		} catch (NameNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		seriesService.getAllSeries().forEach(e->System.out.println(e));
	}

}