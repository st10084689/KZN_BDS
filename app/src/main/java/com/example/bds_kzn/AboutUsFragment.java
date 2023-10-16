package com.example.bds_kzn;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class AboutUsFragment extends Fragment {

    private RecyclerView membersRecView;
    private RecyclerView statementRecView;

    private static final String TAG = "AboutUsFragment";

    private List<Statement> statements = new ArrayList<>();

    private List<Statement> members = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_about_us, container, false);
        init(view);
        return view;
    }
    public void init(View view){
        addStatementList();
        addMembersList();

        statementRecView = view.findViewById(R.id.statementRecycler);
        statementRecView.setHasFixedSize(true);
        statementRecView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));
        AboutUsRecyclerAdapter statementAdapter = new AboutUsRecyclerAdapter(statements);
        statementAdapter.RecyclerType(0);
        statementRecView.setAdapter(statementAdapter);


        statementRecView = view.findViewById(R.id.memberRecycler);
        statementRecView.setHasFixedSize(true);
        statementRecView.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));
        AboutUsRecyclerAdapter memberAdapter = new AboutUsRecyclerAdapter(members);
        statementAdapter.RecyclerType(1);
        statementRecView.setAdapter(memberAdapter);
    }

    public void addStatementList(){

        Statement statementOne = new Statement();
        statementOne.setTitle("Vision Statement");
        statementOne.setLabel("To be the premier provider of services and opportunities that ultimately improve the quality of life of Blind, Deaf, Deafblind persons");
        statementOne.setImage(R.drawable.vision_statement);

        statements.add(statementOne);

        Statement statementTwo = new Statement();
        statementTwo.setTitle("Mission Statement");
        statementTwo.setLabel("Our Mission statement is to ensure that all Blind, Partially-Sighted, Deaf, Hard of Hearing and Deafblind people are integrated into the community, as members who contribute and benefit from society alongside their able- bodied counterparts who do not have these disabilities.");
        statementTwo.setImage(R.drawable.mission_statement);

        statements.add(statementTwo);

        Statement statementThree = new Statement();
        statementThree.setTitle("V N Naik School For The Deaf");
        statementThree.setLabel("Established in 1983, the school is located in Inanda (Outer Durban), educating Hard of Hearing and Deaf learners and hosting children in the school's boarding establishment.");
        statementThree.setImage(R.drawable.v_n_naik_school_for_the_deaf);

        statements.add(statementThree);

        Statement statementFour = new Statement();
        statementFour.setTitle("Arthur Blaxall School For The Blind");
        statementFour.setLabel("The school was established in 1954, and is currently situated in Pietermaritzburg, educating Partially Sighted and Blind learners.");
        statementFour.setImage(R.drawable.blaxall_school_for_the_blind);

        statements.add(statementFour);


        for(int i = 0; i < statements.size(); i++){

            Log.d(TAG, "addStatementList: "+ statements.get(i).getTitle());

        }
    }

    public void addMembersList(){


        Statement memberOne = new Statement();
        memberOne.setTitle("Mr. Vinesh Gokool");
        memberOne.setLabel("Diploma in Business Management and Masters of Administration Degree. He is currently the Managing Director for Atlas Printers, Dynamic Digital Print, Atlas Calenders and Diaries and Atlas Packaging.");
        memberOne.setImage(R.drawable.mr_vinesh_gokool);

        members.add(memberOne);

        Statement memberTwo = new Statement();
        memberTwo.setTitle("Mr. Raven Harkoo");
        memberTwo.setLabel("Bachelor of Law of Degree. Long standing attorney in KwaZulu-Natal and Litigation and Labour Specialist.");
        memberTwo.setImage(R.drawable.mr_raven_harkoo);

        members.add(memberTwo);

        Statement memberThree = new Statement();
        memberThree.setTitle("Mrs A Sewkuran");
        memberThree.setLabel("Diploma in Cost and Management Accounting, A certificate in Adult Education and Training and a BCOM Law Degree.");
        memberThree.setImage(R.drawable.mrs_a_sewkuran);

        members.add(memberThree);

        Statement memberFour = new Statement();
        memberFour.setTitle("Mr Lalit Kumar Thakoprasad Mehta");
        memberFour.setLabel("Baccalaureus Procurations Degree. Is currently an admitted Attorney and admitted Conveyancer");
        memberFour.setImage(R.drawable.mr_lalit_kumar_thakoprasad_mehta);

        members.add(memberFour);

        Statement memberFive = new Statement();
        memberFive.setTitle("Mr Deepanand Nundkissore");
        memberFive.setLabel("Diploma in Accounting and BCOM Accounting Degree. Currently serving as a practicing Chartered Accountant and Certified Financial Planner.");
        memberFive.setImage(R.drawable.mr_deepanand_nundkissore);

        members.add(memberFive);

        Statement memberSix = new Statement();
        memberSix.setTitle("Advocate Ramesh Ramdass");
        memberSix.setLabel("Bachelor of Paedagogics, Bachelor of Medicine and Bachelor of Surgery, Master of Medicine, Diploma in Family Medicine and Certificate in Medical Law. Currently practices as an Advocate, American Board of Independent Medical Examiner and is a member of the Bar Council.");
        memberSix.setImage(R.drawable.advocate_ramesh_ramdass);

        members.add(memberSix);

        Statement memberSeven = new Statement();
        memberSeven.setTitle("Mrs Anuradha Kallideen");
        memberSeven.setLabel("Bachelor of Law of Degree. Currently serving as an attorney in the Private Sector. Sits on the Rental Housing Tribunal.");
        memberSeven.setImage(R.drawable.mrs_anuradha_kallideen);

        members.add(memberSeven);




        Statement memberEight = new Statement();
        memberEight.setTitle("Mrs Anoosha Hanuman");
        memberEight.setLabel("Diploma in Library and information Technology and Diploma in Education.");
        memberEight.setImage(R.drawable.mrs_anoosha_hanuman);

        members.add(memberEight);


    }

}