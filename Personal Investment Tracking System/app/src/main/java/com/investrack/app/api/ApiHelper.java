package com.investrack.app.api;

import android.content.Context;

import com.investrack.app.News;
import com.investrack.app.NewsResponse;
import com.investrack.app.db.DatabaseHelper;
import com.investrack.app.db.Scheme;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiHelper {

    private static volatile ApiHelper INSTANCE;

    private ApiService apiService;
    private Context context;

    public ApiHelper(Context context) {
        this.context = context;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public static ApiHelper getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (ApiHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ApiHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public List<News> getLatestNews() {
        String newsApiUrl = "https://newsapi.org/v2/everything?q=M-" +
                "0..ontrol&sortBy=publishedAt&apiKey=ddcdf6cec45b478f836f1dc5d7a56a9c&pageSize=25";

        Call<NewsResponse> newsListCall = apiService.fetchLatestNews(newsApiUrl);
        List<News> newsList = null;
        try {
            newsList = newsListCall.execute().body().getArticles();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newsList;
    }

    public List<Bank> getAllBanks() {
        List<Bank> bankList = new ArrayList<>();
        Bank bank1 = new Bank(11, "Bank Of Baroda", "Samratnagar", "Ghodasar", 6.5f);
        bankList.add(bank1);
        Bank bank2 = new Bank(12, "ICICI", "Amumaiya soc.", "Maninagar", 8.5f);
        bankList.add(bank2);
        Bank bank3 = new Bank(13, "Yes Bank Ltd", "Shivkrupa soc.", "Gandhinagar", 7f);
        bankList.add(bank3);
        Bank bank4 = new Bank(14, "HDFC", "Pragati soc.", "Bhairavnath", 6f);
        bankList.add(bank4);
        Bank bank5 = new Bank(15, "Canara Bank", "Shivashish soc.", "Kheda", 8f);
        bankList.add(bank5);
        Bank bank6 = new Bank(16, "IDBI", "Shivkrupa soc.", "Gandhinagar", 7f);
        bankList.add(bank6);
        Bank bank7 = new Bank(17, "Allahabad Bank", "Samratnagar", "Allahabad", 6.5f);
        bankList.add(bank7);
        Bank bank8 = new Bank(18, "Andhra Bank", "Parasnagar", "Andhra", 7.5f);
        bankList.add(bank8);
        Bank bank9 = new Bank(19, "Axis Bank", "Pushkar soc.", "Maninagar", 8.5f);
        bankList.add(bank9);
        Bank bank10 = new Bank(20, "UCO Bank", "Bhairavnath", "Maninagar", 7.5f);
        bankList.add(bank10);
        Bank bank11 = new Bank(21, "Union Bank of India", "Amumaiya soc.", "Maninagar", 8.5f);
        bankList.add(bank11);
        Bank bank12 = new Bank(22, "United Bank of India", "Pragati soc.", "Valsad", 6f);
        bankList.add(bank12);
        Bank bank13 = new Bank(23, "Bank of India", "Samratnagar", "Ghodasar", 6.5f);
        bankList.add(bank13);
        Bank bank14 = new Bank(24, "Bank of Maharashtra", "Shivam soc.", "Maharastra", 7.5f);
        bankList.add(bank14);
        Bank bank15 = new Bank(25, "Canara Bank", "Amumaiya soc.", "Maninagar", 8.5f);
        bankList.add(bank15);
        Bank bank16 = new Bank(26, "Central Bank of India", "Pragati soc.", "Rajkot", 6f);
        bankList.add(bank16);
        Bank bank17 = new Bank(27, "City Union Bank", "Pragya soc.", "Kheda", 8f);
        bankList.add(bank17);
        Bank bank18 = new Bank(28, "Corporation Bank", "Shivkrupa soc.", "Gandhinagar", 7f);
        bankList.add(bank18);
        Bank bank19 = new Bank(29, "Syndicate Bank", "Shivkrupa soc.", "Gandhinagar", 7f);
        bankList.add(bank18);
        Bank bank20 = new Bank(30, "Development Credit Bank", "Stubborn soc.", "Umreth", 7.5f);
        bankList.add(bank20);
        Bank bank21 = new Bank(31, "Dhanlaxmi Bank", "Jay ambe soc.", "Maninagar", 8.5f);
        bankList.add(bank21);
        Bank bank22 = new Bank(32, "Federal Bank", "Pragati soc.", "Bhairavnath", 6f);
        bankList.add(bank22);
        Bank bank23 = new Bank(33, "Indian Bank", "Shivashish soc.", "Kheda", 8f);
        bankList.add(bank23);
        Bank bank24 = new Bank(34, "Indian Overseas Bank", "Shiv soc.", "Gandhinagar", 7f);
        bankList.add(bank24);
        Bank bank25 = new Bank(35, "IndusInd Bank", "V.K.nagar", "Ghodasar", 6.5f);
        bankList.add(bank25);
        Bank bank26 = new Bank(36, "ING Vysya Bank", "Parasnagar", "Isanpur", 7.5f);
        bankList.add(bank26);
        Bank bank27 = new Bank(37, "Jammu and Kashmir Bank", "Amumaiya soc.", "J&K", 8.5f);
        bankList.add(bank27);
        Bank bank28 = new Bank(38, "Karnataka Bank Ltd", "Swarnim soc.", "Karnataka", 6f);
        bankList.add(bank28);
        Bank bank29 = new Bank(39, "Karur Vysya Bank", "Shivashish soc.", "Kheda", 8f);
        bankList.add(bank29);
        Bank bank30 = new Bank(40, "Kotak Bank", "Vansh soc.", "Gandhinagar", 7f);
        bankList.add(bank30);
        Bank bank31 = new Bank(41, "Laxmi Vilas Bank", "Samratnagar", "Ghodasar", 6.5f);
        bankList.add(bank31);
        Bank bank32 = new Bank(42, "Oriental Bank of Commerce", "Parasnagar", "Isanpur", 7.5f);
        bankList.add(bank32);
        Bank bank33 = new Bank(43, "State Bank of India", "Shivkrupa soc.", "Gandhinagar", 7f);
        bankList.add(bank33);

        List<Scheme> schemes = new ArrayList<>();
        for (Bank bank : bankList) {
            schemes.add(toScheme(bank));
        }

        DatabaseHelper.getDatabase(context).schemeDao().insertAll(schemes);
        return bankList;
    }

    public List<MutualFund> getAllMutualFunds() {
        List<MutualFund> mutualfundList = new ArrayList<>();
        MutualFund mf1 = new MutualFund(1001, "Aditya Birla SL Frontline Equity Fund(G)", "Aditya Birla", 226.74f);
        mutualfundList.add(mf1);
        MutualFund mf2 = new MutualFund(1002, "Baroda Pioneer Large Cap Fund(G)", "Bank of Baroda", 14.52f);
        mutualfundList.add(mf2);
        MutualFund mf3 = new MutualFund(1003, "HDFC Top 200 Fund(G)", "HDFC", 502.703f);
        mutualfundList.add(mf3);
        MutualFund mf4 = new MutualFund(1004, "ICICI Pru Focused Bluechip Equity Fund(G)", "ICICI", 42.19f);
        mutualfundList.add(mf4);
        MutualFund mf5 = new MutualFund(1005, "Reliance Top 200 Fund(G)", "Reliance", 35.45f);
        mutualfundList.add(mf5);
        MutualFund mf6 = new MutualFund(1006, "Reliance Top 200 Fund(G)", "Reliance", 6.5f);
        mutualfundList.add(mf6);
        MutualFund mf7 = new MutualFund(1007, "HSBC Infrastructure Equity Fund - Growth", "HSBC", 18.0429f);
        mutualfundList.add(mf7);
        MutualFund mf8 = new MutualFund(1008, "UTI-Transpotation and Logistics Fund-Growth Option", "UTI", 97.6893f);
        mutualfundList.add(mf8);
        MutualFund mf9 = new MutualFund(1009, "Sundaram Small Cap Fund-Regular Growth", "Sundaram", 84.2714f);
        mutualfundList.add(mf9);
        MutualFund mf10 = new MutualFund(1010, "Sundaram Small Cap Fund -iNST Growth", "Sundaram", 89.0678f);
        mutualfundList.add(mf10);
        MutualFund mf11 = new MutualFund(1011, "HSBC Small Cap Equity Fund - Growth", "HSBC", 50.6447f);
        mutualfundList.add(mf11);
        MutualFund mf12 = new MutualFund(1012, "Aditya Birla Sun Life Pure Value Fund - Growth Option", "Aditya Birla", 51.9619f);
        mutualfundList.add(mf12);
        MutualFund mf13 = new MutualFund(1013, "BOI AXA Manufacturing & Infrastructure Fund-Growth", "BOI", 15.73f);
        mutualfundList.add(mf13);
        MutualFund mf14 = new MutualFund(1014, "IDFC Infrastructure Fund-Regular Plan-Growth", "IDFC", 15.17f);
        mutualfundList.add(mf14);
        MutualFund mf15 = new MutualFund(1015, "Union Small Cap Fund - Regular Plan - Growth Option", "Union", 13.9f);
        mutualfundList.add(mf15);
        MutualFund mf16 = new MutualFund(1016, "Aditya Birla Sun Life Small Cap Fund - GROWTH", "Aditya Birla", 35.4813f);
        mutualfundList.add(mf16);
        MutualFund mf17 = new MutualFund(1017, "DSP Small Cap Fund - Regular - Growth", "DSP", 55.954f);
        mutualfundList.add(mf17);
        MutualFund mf18 = new MutualFund(1018, "DSP World Energy Fund - Regular Plan - Growth", "DSP", 13.0149f);
        mutualfundList.add(mf18);
        MutualFund mf19 = new MutualFund(1019, "Reliance Power & Infra Fund-Growth Plan -Growth Option", "Reliance ", 98.2788f);
        mutualfundList.add(mf19);
        MutualFund mf20 = new MutualFund(1020, "UTI Mid Cap Fund-Growth Option", "UTI", 100.4216f);
        mutualfundList.add(mf20);
        MutualFund mf21 = new MutualFund(1021, "ICICI Prudential Smallcap Fund - Growth", "ICICI", 24.97f);
        mutualfundList.add(mf21);
        MutualFund mf22 = new MutualFund(1022, "IDBI Small Cap Fund Growth Regular", "IDBI", 9.55f);
        mutualfundList.add(mf22);
        MutualFund mf23 = new MutualFund(1023, "BOI AXA Large & Mid Cap Equity Fund Regular Plan- Growth", "BOI", 33.52f);
        mutualfundList.add(mf23);
        MutualFund mf24 = new MutualFund(1024, "BOI AXA Tax Advantage Fund-Regular Plan- Growth", "BOI", 50.51f);
        mutualfundList.add(mf24);
        MutualFund mf25 = new MutualFund(1025, "Indiabulls Value Discovery Fund - Regular Plan - Growth Option", "India Bulls", 11.8488f);
        mutualfundList.add(mf25);
        MutualFund mf26 = new MutualFund(1026, "Baroda Mid-cap Fund- Plan A - Growth Option", "Bank of Baroda", 8.99f);
        mutualfundList.add(mf26);
        MutualFund mf27 = new MutualFund(1027, "Edelweiss Mid Cap Fund - Regular Plan - Growth Option", "Edelweiss", 26.526f);
        mutualfundList.add(mf27);
        MutualFund mf28 = new MutualFund(1028, "Reliance Small Cap Fund - Growth Plan - Growth Option", "Reliance", 40.5292f);
        mutualfundList.add(mf28);
        MutualFund mf29 = new MutualFund(1029, "L&T Emerging Businesses Fund - Regular Plan - Growth", "L&T", 24.819f);
        mutualfundList.add(mf29);
        MutualFund mf30 = new MutualFund(1030, "BOI AXA Large & Mid Cap Equity Fund Eco Plan- Growth", "BOI", 35.47f);
        mutualfundList.add(mf30);
        MutualFund mf31 = new MutualFund(1031, "HDFC Infrastructure Fund - Growth Option", "HDFC", 17.146f);
        mutualfundList.add(mf31);
        MutualFund mf32 = new MutualFund(1032, "Invesco India Feeder - Invesco Pan European Equity Fund - Regular Plan - Growth Option", "Invesco", 10.656f);
        mutualfundList.add(mf32);
        MutualFund mf33 = new MutualFund(1033, "Kotak-Small Cap Fund - Growth", "Kotak", 71.943f);
        mutualfundList.add(mf33);
        MutualFund mf34 = new MutualFund(1034, "Kotak World Gold Fund - Standard Plan - Growth Option", "Kotak", 7.907f);
        mutualfundList.add(mf34);
        MutualFund mf35 = new MutualFund(1035, "Tata India Consumer Fund-Regular Plan-Growth", "Tata", 17.027f);
        mutualfundList.add(mf35);
        MutualFund mf36 = new MutualFund(1036, "DHFL Pramerica Euro Equity Fund - Growth", "DHFL", 13.19f);
        mutualfundList.add(mf36);
        MutualFund mf37 = new MutualFund(1037, "Kotak Global Emerging Market Fund - Growth", "Kotak", 15.366f);
        mutualfundList.add(mf37);
        MutualFund mf38 = new MutualFund(1038, "Franklin India Feeder - Franklin European Growth Fund - Growth", "Franklin Indian Feeder", 9.5695f);
        mutualfundList.add(mf38);
        MutualFund mf39 = new MutualFund(1039, "SBI Small Cap Fund - Regular Plan - Growth", "SBI", 52.2874f);
        mutualfundList.add(mf39);
        MutualFund mf40 = new MutualFund(1040, "Sundaram Mid Cap Fund -Growth", "Sundaram", 468.109f);
        mutualfundList.add(mf40);
        MutualFund mf41 = new MutualFund(1041, "Principal Global Opportunities Fund-Growth Option", "Principal", 28.3931f);
        mutualfundList.add(mf41);
        MutualFund mf42 = new MutualFund(1042, "Edelweiss Europe Dynamic Equity Offshore Fund - Growth Option - Regular Plan", "Edelweiss", 10.7953f);
        mutualfundList.add(mf42);
        MutualFund mf43 = new MutualFund(1043, "Aditya Birla Sun Life Commodity Equities Fund - Global Agri Plan - Growth - Regular Plan", "Aditya Birla", 23.2365f);
        mutualfundList.add(mf43);
        MutualFund mf44 = new MutualFund(1044, "HSBC Brazil Fund-Growth", "HSBC", 8.4549f);
        mutualfundList.add(mf44);
        MutualFund mf45 = new MutualFund(1045, "DHFL Pramerica Midcap Opportunities Fund - Regular Plan - Growth Option", "DHFL", 18.29f);
        mutualfundList.add(mf45);
        MutualFund mf46 = new MutualFund(1046, "Franklin India Smaller Companies Fund-Growth", "Franklin India", 54.7854f);
        mutualfundList.add(mf46);
        MutualFund mf47 = new MutualFund(1047, "Aditya Birla Sun Life Infrastructure Fund-Growth", "Aditya Birla", 32.46f);
        mutualfundList.add(mf47);
        MutualFund mf48 = new MutualFund(1048, "BOI AXA MID & SMALL CAP EQUITY & DEBT FUND - REGULAR PLAN GROWTH", "BOI", 13.0f);
        mutualfundList.add(mf48);
        MutualFund mf49 = new MutualFund(1049, "Aditya Birla SUn Life Global Commodities Fund - Regular Plan - Growth Option", "Aditya Birla", 12.5025f);
        mutualfundList.add(mf49);
        MutualFund mf50 = new MutualFund(1050, "DSP World Agriculture Fund - Regular Plan - Growth", "DSP", 15.9673f);
        mutualfundList.add(mf50);
        MutualFund mf51 = new MutualFund(1051, "UTI Healthcare Fund - Regular Plan - Growth Option", "UTI", 85.838f);
        mutualfundList.add(mf51);
        MutualFund mf52 = new MutualFund(1052, "IDBI Midcap Fund Growth Regular", "IDBI", 10.86f);
        mutualfundList.add(mf52);
        MutualFund mf53 = new MutualFund(1053, "SBI HEALTHCARE OPPORTUNITIES FUND - REGULAR PLAN -GROWTH", "SBI", 120.3595f);
        mutualfundList.add(mf53);
        MutualFund mf54 = new MutualFund(1054, "UTI India Lifestyle Fund - Regular Plan - Growth Option", "UTI", 25.4683f);
        mutualfundList.add(mf54);
        MutualFund mf55 = new MutualFund(1055, "L&T Infrastructure Fund - Regular Plan - Growth", "L&T", 15.99f);
        mutualfundList.add(mf55);
        MutualFund mf56 = new MutualFund(1056, "L&T Business Cycles Fund - Regular Plan - Growth", "L&T", 15.27f);
        mutualfundList.add(mf56);

        List<Scheme> schemes = new ArrayList<>();
        for (MutualFund mutualfund : mutualfundList) {
            schemes.add(toScheme(mutualfund));
        }

        DatabaseHelper.getDatabase(context).schemeDao().insertAll(schemes);
        return mutualfundList;

    }

    public List<Stock> getAllStocks() {
        List<Stock> stockList = new ArrayList<>();
        Stock stock1 = new Stock(2001, "TATA STEEL", "NSE", 550.50f);
        stockList.add(stock1);
        Stock stock2 = new Stock(2002, "ULTRACEMCO", "NSE", 4203.90f);
        stockList.add(stock2);
        Stock stock3 = new Stock(2003, "BAJFINANCE", "BSE", 3123.00f);
        stockList.add(stock3);
        Stock stock4 = new Stock(2004, "HINDALCO", "BSE", 216.20f);
        stockList.add(stock4);
        Stock stock5 = new Stock(2005, "VEDL", "NSE", 192.10f);
        stockList.add(stock5);
        Stock stock6 = new Stock(2006, "JSWSTEEL", "NSE", 295.35f);
        stockList.add(stock6);
        Stock stock7 = new Stock(2007, "EICHERMOT", "NSE", 20989.00f);
        stockList.add(stock7);
        Stock stock8 = new Stock(2008, "IOC", "BSE", 158.85f);
        stockList.add(stock8);
        Stock stock9 = new Stock(2009, "CIPLA", "BSE", 532.10f);
        stockList.add(stock9);
        Stock stock10 = new Stock(2010, "GRASIM", "NSE", 851.50f);
        stockList.add(stock10);
        Stock stock11 = new Stock(2011, "ADANIPORTS", "NSE", 387.45f);
        stockList.add(stock11);
        Stock stock12 = new Stock(2012, "TCS", "NSE", 2051.00f);
        stockList.add(stock12);
        Stock stock13= new Stock(2013, "INDUSINDBK", "BSE", 1765.00f);
        stockList.add(stock13);
        Stock stock14 = new Stock(2014, "INFY", "BSE", 759.55f);
        stockList.add(stock14);
        Stock stock15 = new Stock(2015, "WIPRO", "NSE", 262.50f);
        stockList.add(stock15);
        Stock stock16 = new Stock(2016, "BAJAJFINSV", "NSE", 7408.00f);
        stockList.add(stock16);
        Stock stock17 = new Stock(2017, "HDFCBANK", "NSE", 2310.00f);
        stockList.add(stock17);
        Stock stock18 = new Stock(2018, "BPCL", "BSE", 358.25f);
        stockList.add(stock18);
        Stock stock19 = new Stock(2019, "HDFC", "BSE", 2064.50f);
        stockList.add(stock19);
        Stock stock20 = new Stock(2020, "KOTAKBANK", "NSE", 1337.10f);
        stockList.add(stock20);
        Stock stock21 = new Stock(2021, "UPL", "NSE", 941.35f);
        stockList.add(stock21);
        Stock stock22 = new Stock(2022, "IBULHSGFIN", "NSE", 906.80f);
        stockList.add(stock22);
        Stock stock23 = new Stock(2023, "ONGC", "BSE", 156.70f);
        stockList.add(stock23);
        Stock stock24 = new Stock(2024, "GAIL", "BSE", 349.00f);
        stockList.add(stock24);
        Stock stock25 = new Stock(2025, "ICICIBANK", "NSE", 391.75f);
        stockList.add(stock25);
        Stock stock26 = new Stock(2026, "M&M", "NSE", 658.10f);
        stockList.add(stock26);
        Stock stock27 = new Stock(2027, "TECHM", "NSE", 778.70f);
        stockList.add(stock27);
        Stock stock28 = new Stock(2028, "LT", "BSE", 1375.40f);
        stockList.add(stock28);
        Stock stock29 = new Stock(2029, "BHARTIARTL", "BSE", 358.30f);
        stockList.add(stock29);
        Stock stock30 = new Stock(2030, "BAJAJ-AUTO", "NSE", 2867.00f);
        stockList.add(stock30);
        Stock stock31 = new Stock(2031, "TITAN", "NSE", 1109.00f);
        stockList.add(stock31);
        Stock stock32 = new Stock(2032, "AXISBANK", "NSE", 764.00f);
        stockList.add(stock32);
        Stock stock33 = new Stock(2033, "ITC", "BSE", 295.45f);
        stockList.add(stock33);
        Stock stock34 = new Stock(2034, "RELIANCE", "BSE", 1355.85f);
        stockList.add(stock34);
        Stock stock35 = new Stock(2035, "INFRATEL", "NSE", 316.60f);
        stockList.add(stock35);
        Stock stock36 = new Stock(2036, "COALINDIA", "NSE", 234.50f);
        stockList.add(stock36);
        Stock stock37 = new Stock(2037, "MARUTI", "NSE", 7103.70f);
        stockList.add(stock37);
        Stock stock38 = new Stock(2038, "TATAMOTORS", "BSE", 205.85f);
        stockList.add(stock38);
        Stock stock39 = new Stock(2039, "HINDUNILVR", "BSE", 1662.55f);
        stockList.add(stock39);
        Stock stock40 = new Stock(2040, "ASIANPAINT", "NSE", 1513.90f);
        stockList.add(stock40);
        Stock stock41 = new Stock(2041, "SUNPHARMA", "NSE", 463.70f);
        stockList.add(stock41);
        Stock stock42 = new Stock(2042, "HCLTECH", "NSE", 1092.00f);
        stockList.add(stock42);
        Stock stock43 = new Stock(2043, "YESBANK", "BSE", 266.50f);
        stockList.add(stock43);
        Stock stock44 = new Stock(2044, "HEROMOTOCO", "BSE", 2622.00f);
        stockList.add(stock44);
        Stock stock45 = new Stock(2045, "NTPC", "NSE", 134.85f);
        stockList.add(stock45);
        Stock stock46 = new Stock(2046, "DRREDDY", "NSE", 2752.95f);
        stockList.add(stock46);
        Stock stock47 = new Stock(2047, "SBIN", "NSE", 317.70f);
        stockList.add(stock47);
        Stock stock48 = new Stock(2048, "ZEEL", "BSE", 411.75f);
        stockList.add(stock48);
        Stock stock49 = new Stock(2049, "BRITANNIA", "BSE", 2998.00f);
        stockList.add(stock49);
        Stock stock50 = new Stock(2050, "POWERGRID", "NSE", 196.35f);
        stockList.add(stock50);

        List<Scheme> schemes = new ArrayList<>();
        for (Stock stock : stockList) {
            schemes.add(toScheme(stock));
        }

        DatabaseHelper.getDatabase(context).schemeDao().insertAll(schemes);
        return stockList;
    }

    public static Scheme toScheme(Bank bank) {
        return new Scheme(bank.getId(), bank.getName(), Scheme.SCHEME_FD, bank.getName(), null, null, bank.getRateOfInterest());
    }

    public static Scheme toScheme(MutualFund mf) {
        return new Scheme(mf.getId(), mf.getName(), Scheme.SCHEME_MF, null, mf.getFundhouse(), null, mf.getNav());

    }

    public static Scheme toScheme(Stock stock) {
        return new Scheme(stock.getId(), stock.getName(), Scheme.SCHEME_STOCK, null, null, stock.getMarket(), stock.getSharePrice());

    }
}
