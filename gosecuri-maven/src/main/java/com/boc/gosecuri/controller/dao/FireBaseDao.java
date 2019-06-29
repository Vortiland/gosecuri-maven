package com.boc.gosecuri.controller.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import util.FirebaseUtil;
import util.exception.TechnicalException;

public class FireBaseDao {

	public List<Object> getTrucs() throws TechnicalException{
		FirebaseUtil firebaseUtil = new FirebaseUtil();
		DatabaseReference labase = firebaseUtil.getInstance().firebaseDatabase();

		final List<Object> result = new ArrayList<Object>();


		//beurk
		final Semaphore semaphore = new Semaphore(0);
		labase.addValueEventListener(new ValueEventListener() {


			public void onDataChange(DataSnapshot snapshot) {
				try {
					System.out.println("found {} "+ snapshot.child("characters").getChildrenCount());
					String string = snapshot.toString();

					for (DataSnapshot postSnapshot: snapshot.child("characters").getChildren()) {
						Object character = postSnapshot.getValue(Object.class);
						System.out.println("--> " + character.toString());
						result.add(character);
					}
				}catch (Exception e) {
					System.out.println("erreur désérialisation"+e);
				}finally {
					semaphore.release();
					
				}
			}



			public void onCancelled(DatabaseError error) {
				System.out.println("fail "+error.getMessage());

			}
		});
		
		
		try {
			semaphore.acquire();
		} catch (InterruptedException e) {
			System.out.println("aarg" + e.getMessage());
			TechnicalException.throwTechnicalException("aaaarg", e);
		}
		return result;
	}
}
