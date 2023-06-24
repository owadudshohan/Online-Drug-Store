package com.app.onlinepharmecy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.onlinepharmecy.Adapters.MedicineAdapter;
import com.app.onlinepharmecy.Models.CartData;
import com.app.onlinepharmecy.Models.MedicineModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.app.onlinepharmecy.R;
import com.app.onlinepharmecy.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.UUID;

public class MainActivity extends AppCompatActivity implements MedicineAdapter.OnItemClickListener {

    ActivityMainBinding binding;
    ArrayList<MedicineModel> list;
    Dialog dialog_des;
    Dialog dialog_loading,dialog_exit;
    TextView m_name,m_dose,m_des,m_price,m_txt_amnt,txt_mt_price;
    ImageView m_img;
    CardView minus,add,m_cancel,m_add_to_cart;
    int m_amount=1;

    FirebaseDatabase database;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        database = FirebaseDatabase.getInstance();
        auth= FirebaseAuth.getInstance();
        list=new ArrayList();

        dialog_loading = new Dialog(this);
        dialog_loading.setContentView(R.layout.dialog_loading);
        dialog_loading.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog_loading.setCancelable(false);

        dialog_exit = new Dialog(this);
        dialog_exit.setContentView(R.layout.dialog_exits);
        dialog_exit.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog_exit.setCancelable(false);

        list.add(new MedicineModel("Napa EXTRA","500mg","30",R.drawable.p1,"Napa EXTRA is a combination of two medicines used in the treatment of headache. It helps relieve headache by blocking the release of certain chemical messengers that causes headache. Your doctor will advise how much Napa EXTRA you need to take. It can be taken with or without food. You should take the medicine regularly while you do need it and try not to miss doses as it will become less effective. "));
        list.add(new MedicineModel("E Cap","400mg","105",R.drawable.p2,"Vitamin E plays a role in protecting red blood cells against hemolysis; has protective effects against free radicals on polyunsaturated fatty acids found in cell membranes; plays a role in preventing oxidation of vitamin A and C."));
        list.add(new MedicineModel("Napa","500mg","2",R.drawable.p3,"Napa is a medicine used to relieve pain and to reduce fever. It is used to treat many conditions such as headache, body ache, toothache and common cold. Napa may be prescribed alone or in combination with another medicine. You should take it regularly as advised by your doctor. It is usually best taken with food otherwise it may upset your stomach. Do not take more or use it for longer than recommended."));
        list.add(new MedicineModel("Seclo","20mg","60",R.drawable.p4,"Seclo 20 is a medicine that reduces the amount of acid produced in your stomach. It is used for treating acid-related diseases of the stomach and intestine such as heartburn, acid reflux, peptic ulcer disease, and some other stomach conditions associated with excessive acid production. Seclo 20 is also used to prevent stomach ulcers and acidity that may be seen with the prolonged use of pain-killers. It belongs to a class of medicines known as proton pump inhibitors (PPIs). "));
        list.add(new MedicineModel("Calbo D","500mg","240",R.drawable.p5,"Calbo D is a prescription medicine. It is a combination of medicines that are prescribed to treat osteoporosis. It ensures the proper growth and functioning of the joints and bones. Calbo D should be used in the dose and duration as prescribed by your doctor. It is advised to take this medicine at a fixed time. This medicine should not be taken in more than the recommended dose."));
        list.add(new MedicineModel("Coralcal D","500mg","120",R.drawable.p6,"Coralcal-D is a prescription medicine. It is a combination of medicines that are prescribed to treat osteoporosis. It ensures the proper growth and functioning of the joints and bones. Coralcal-D should be used in the dose and duration as prescribed by your doctor. It is advised to take this medicine at a fixed time. This medicine should not be taken in more than the recommended dose. "));
        list.add(new MedicineModel("Ceevit","250mg","19",R.drawable.p7,"Treatment or prevention of, Vitamin C Deficiency, Scurvy, Infection, Trauma, Burns, Cold exposure, Following Surgery, common cold, Fever, scurvy, Stress, Cancer, Methaemoglobinaemia and Children receiving unfortified formulas. Also indicated in, Hematuria, Dental Caries, Gum Diseases, Pyorrhea, Acne, Infertility, Atherosclerosis, Fractures, Leg ulcers, Hay fever, Vascular thrombosis prevention, Levodopa toxicity, Arsenic toxicity."));
        list.add(new MedicineModel("Emcon","1.5mg","70",R.drawable.p8,"Emcon 1 is an emergency birth control pill. It provides a safe and effective way to prevent an unintended pregnancy after unprotected sex or contraceptive failure. However, it should not be used as regular contraception. It works to prevent pregnancy by temporarily stopping the release of an egg from the ovary or by preventing fertilization (union) of the egg by sperm. It comes as a single dose and can be taken with or without food. "));
        list.add(new MedicineModel("Rosen ","28-3mg","399",R.drawable.p9,"Rosen 28 is a combination of two medicines used for contraception (to prevent pregnancy) and in the treatment of irregular periods. It helps to prevent release and fertilization of the egg. Rosen 28 can be taken with or without food, but take it at the same time to get the most benefit. It should be taken as your doctor's advice. You should have to start taking the pill on the day one of your menstrual cycle and continue taking it for whole month and start with new pack once the pack get finished. If you experience vomiting with in 4 hours of dose intake, take another tablet. "));
        list.add(new MedicineModel("Losectil","20mg","50",R.drawable.p10,"Losectil 20 is a medicine that reduces the amount of acid produced in your stomach. It is used for treating acid-related diseases of the stomach and intestine such as heartburn, acid reflux, peptic ulcer disease, and some other stomach conditions associated with excessive acid production. Losectil 20 is also used to prevent stomach ulcers and acidity that may be seen with the prolonged use of pain-killers. It belongs to a class of medicines known as proton pump inhibitors (PPIs). This medicine should be taken one hour before a meal, preferably in the morning."));
        list.add(new MedicineModel("Orsaline N","10.5gm","90",R.drawable.p11,"Potassium chloride is a major cation of the intracellular fluid. It plays an active role in the conduction of nerve impulses in the heart, brain and skeletal muscle; contraction of cardiac skeletal and smooth muscles; maintenance of normal renal function, acid-base balance, carbohydrate metabolism and gastric secretion. Sodium chloride is the major extracellular cation. It is important in electrolyte and fluid balance, osmotic pressure control and water distribution as it restores sodium ions. It is used as a source of electrolytes and water for hydration, treatment of metabolic acidosis, priming solution in haemodialysis and treatment of hyperosmolar diabetes. "));
        list.add(new MedicineModel("Cora DX","600mg","160",R.drawable.p12,"Calcium /vitamin D3 prevents or treats negative Ca balance. It also helps facilitate nerve and muscle performance as well as normal cardiac function. Bone mineral component; cofoactor in enzymatic reactions, essential for neurotransmission, muscle contraction, and many signal transduction pathways. Vitamin D3 is a fat-soluble sterol. It is necessary for the regulation and regulation of calcium and phosphate homoeostasis and bone mineralisation. Vitamin D is also essential for healthy bones as it aids in Calcium absorption from the GI tract. In addition to this it stimulates bone formation. Clinical studies also show that Calcium and Vitamin D has synergistic effects on bone growth as well as in Osteoporosis and fracture prevention."));
        list.add(new MedicineModel("Entacyd Plus","400mg","25",R.drawable.p13,"Aluminium Hydroxide: Enhanced absorption with citrates or ascorbic acid. Decreases absorption of allopurinol, tetracyclines, quinolones, cephalosporins, biphosphonate derivatives, corticosteroids, cyclosporin, delavirdine, Fe salts, imidazole antifungals, isoniazid, mycophenolate, penicillamine, phosphate supplements, phenytoin, phenothiazines, trientine. Magnesium Hydroxide: Decreases absorption of tetracyclines and biphosphonates. Separate administration of these and other drugs by around 2 hr. Simethicon: None well documented."));
        list.add(new MedicineModel("Rocal D","500mg","80",R.drawable.p14,"Rocal D is a prescription medicine. It is a combination of medicines that are prescribed to treat osteoporosis. It ensures the proper growth and functioning of the joints and bones. Rocal D should be used in the dose and duration as prescribed by your doctor. It is advised to take this medicine at a fixed time. This medicine should not be taken in more than the recommended dose."));
        list.add(new MedicineModel("Marincal DX","600mg","150",R.drawable.p15,"Calcium /vitamin D3 prevents or treats negative Ca balance. It also helps facilitate nerve and muscle performance as well as normal cardiac function. Bone mineral component; cofoactor in enzymatic reactions, essential for neurotransmission, muscle contraction, and many signal transduction pathways. Vitamin D3 is a fat-soluble sterol. It is necessary for the regulation and regulation of calcium and phosphate homoeostasis and bone mineralisation. Vitamin D is also essential for healthy bones as it aids in Calcium absorption from the GI tract. In addition to this it stimulates bone formation. Clinical studies also show that Calcium and Vitamin D has synergistic effects on bone growth as well as in Osteoporosis and fracture prevention."));
        list.add(new MedicineModel("Thermometer Digital Flexible","","250",R.drawable.p16,"Digital Thermometer"));
        list.add(new MedicineModel("Alcohol Pad","","90",R.drawable.p17,"Thicker and softer than some other swabs, BD Alcohol Swab ensures reliable site preparation for safe, hygienic insulin injections. Alcohol Swab is also excellent for many other household uses."));
        list.add(new MedicineModel("Sepnil Face Mask","","350",R.drawable.p18,"The 3 layerswith medical grade face mask ensures 99% protection from germs and bacteria. "));
        list.add(new MedicineModel("Accu Chek Active Blood Glucometer","","3200",R.drawable.p19,"The Accu-check active blood glucose meter delivers accurate results easy to use and upgraded with no coding feature."));
        list.add(new MedicineModel("Accu Chek Active Strip","","1400",R.drawable.p20," Accu-Chek Active test strips only require a very small blood sample to perform a blood glucose test – just 1-2µl of blood. "));
//MRP-৳


        dialog_des = new Dialog(this);
        dialog_des.setContentView(R.layout.dialog_details);
        dialog_des.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog_des.setCancelable(false);

        MedicineAdapter adapter=new MedicineAdapter(list,MainActivity.this);
        binding.rvMedicine.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        binding.rvMedicine.setHasFixedSize(true);
        binding.rvMedicine.setLayoutManager(new GridLayoutManager(MainActivity.this,2));

        binding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                Intent i22=new Intent(MainActivity.this,SignIn.class);
                startActivity(i22);
                finish();

            }
        });

        binding.cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i22=new Intent(MainActivity.this,CartActivity.class);
                startActivity(i22);


            }
        });
    }

    @Override
    public void onItemClick(MedicineModel item) {

        dialog_des.show();

        m_name=dialog_des.findViewById(R.id.m_name);
        m_dose=dialog_des.findViewById(R.id.m_dose);
        m_des=dialog_des.findViewById(R.id.m_des);
        m_img=dialog_des.findViewById(R.id.m_img);
        m_price=dialog_des.findViewById(R.id.m_price);
        m_txt_amnt=dialog_des.findViewById(R.id.m_total);
        txt_mt_price=dialog_des.findViewById(R.id.mt_price);

        minus=dialog_des.findViewById(R.id.btn_minus);
        add=dialog_des.findViewById(R.id.btn_add);
        m_cancel=dialog_des.findViewById(R.id.m_cancel);
        m_add_to_cart=dialog_des.findViewById(R.id.m_add_cart);

        m_name.setText(item.getM_name());
        m_dose.setText(item.getDose());
        m_des.setText(item.getM_des());
        m_img.setImageResource(item.getImage());
        m_price.setText("Price "+item.getPrice()+" MRP-৳");
        txt_mt_price.setText("Total Price "+item.getPrice()+" MRP-৳");

        int total_price=0;

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            if (m_amount>1){
                m_amount--;

                m_txt_amnt.setText(""+m_amount);
                txt_mt_price.setText("Total Price "+(m_amount*Integer.parseInt(item.getPrice()))+" MRP-৳");

            }



            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                m_amount++;

                m_txt_amnt.setText(""+m_amount);
                txt_mt_price.setText("Total Price "+(m_amount*Integer.parseInt(item.getPrice()))+" MRP-৳");

            }
        });

        m_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m_amount=1;
                m_txt_amnt.setText(""+m_amount);
                dialog_des.cancel();
            }
        });

       m_add_to_cart.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               dialog_loading.show();


               CartData data=new CartData(item.getM_name(),item.getDose(),String.valueOf(m_amount),(m_amount*Integer.parseInt(item.getPrice())));

               database.getReference().child("User").child(auth.getUid()).child("Cart").child(UUID.randomUUID().toString()).setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                   @Override
                   public void onComplete(@NonNull Task<Void> task) {

                       dialog_loading.cancel();
                       dialog_des.cancel();
                       Toast.makeText(MainActivity.this, "Item Added", Toast.LENGTH_SHORT).show();

                       Intent i1=new Intent(MainActivity.this,CartActivity.class);
                       startActivity(i1);
                       m_amount=1;
                       m_txt_amnt.setText(""+m_amount);

                   }
               });







           }
       });


    }
}