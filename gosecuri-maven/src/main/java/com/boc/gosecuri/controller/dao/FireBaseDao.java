package com.boc.gosecuri.controller.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Semaphore;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import util.FirebaseUtil;
import util.exception.TechnicalException;

public class FireBaseDao {

	public List<Object> getTrucs() throws TechnicalException{
		FirebaseUtil firebaseUtil = new FirebaseUtil();
		Firestore fireStore = firebaseUtil.getInstance().firebaseDatabase();

		final List<Object> result = new ArrayList<Object>();

		
		Iterable<CollectionReference> collections = fireStore.getCollections();
		for (CollectionReference col:collections) {
			System.out.println("col "+col.getId()+" "+col.getPath());
		}
		
		CollectionReference equipement = fireStore.collection("equipement");
		ApiFuture<QuerySnapshot> apiFuture = equipement.get();
		try {
			QuerySnapshot querySnapshot = apiFuture.get();
			 List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
			 
			 for (QueryDocumentSnapshot doc:documents) {
				  System.out.println("Document data: " + doc.getData());
				  result.add(doc.get("id"));
			 }
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	}
}
