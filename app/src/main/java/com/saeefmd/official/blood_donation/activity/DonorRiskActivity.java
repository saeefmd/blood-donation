package com.saeefmd.official.blood_donation.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.saeefmd.official.blood_donation.R;

public class DonorRiskActivity extends AppCompatActivity {

    private PDFView donorRiskPdfView;

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_risk);

        //donorRiskPdfView = findViewById(R.id.donor_risk_pdf_view);

        /*donorRiskPdfView.fromAsset("Donor_Factors.pdf")
                .load();*/

        mWebView = findViewById(R.id.donor_factors_web_view);

        mWebView.loadUrl("https://www.mayoclinic.org/tests-procedures/blood-donation/about/pac-20385144");
    }
}