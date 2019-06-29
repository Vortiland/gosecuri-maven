package util;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import util.exception.TechnicalException;

public class FirebaseUtil {

	String KEY_FILE="ewogICJ0eXBlIjogInNlcnZpY2VfYWNjb3VudCIsCiAgInByb2plY3RfaWQiOiAiamF2YS11bi1lcHNpLWIzIiwKICAicHJpdmF0ZV9rZXlfaWQiOiAiZDA0ZTU1Y2NhNjUwZTUyZTYwMzYzMzU0ZjRhYjI5YWRjZmFkYmQwMiIsCiAgInByaXZhdGVfa2V5IjogIi0tLS0tQkVHSU4gUFJJVkFURSBLRVktLS0tLVxuTUlJRXZ3SUJBREFOQmdrcWhraUc5dzBCQVFFRkFBU0NCS2t3Z2dTbEFnRUFBb0lCQVFEWE93L2xsTWRZcmMxQ1xuWUZvdEIyTnl4K1V2ZWtKdGEyRHlENXplMXI1MnFta2wxb1hCWmUwNlVoTGplYXBjS3RWV3FrMGp1dktQbGs2dFxuQjZmYWx2LzVobHc1YnFGUFkydDdTU1hMd0ppWE9LUEJFeGJqQ2N6Vi9IMi8vMGJ6VmFEdmlVVExsWEg3U2dRRlxuUXdycnVSSWNKVkJ6d3hOMU9YOGZYNXdKMXkxT0gvZkxqZytIZi9seithb1pYNWxKVnk5UDBQczg0T2t1aGxWSlxuNlVFNE90YmI2d2ZiTWNrV1VEdHpUbEZEVEV5RFp3Y2gzNVhLRWhSRmlrY09MYUVMc1VTK1l3eCtsYVlBQUNjb1xubENaRVliRHYxREpCSk50R1p2aVNXeDc2TTY1YzZxbEIrMnFNZXhxQVpBRDJmbVcwMklKSFVxR3pNWGhUSjNBc1xuRW5JcW92WWZBZ01CQUFFQ2dnRUFBM2VJYTYvNEtoTXY5dWdWd1J0RHNkU2dlaUdqc2ttRUxVSUNkVFhMcVo1WVxuYit0RENrNGtWOGZTQUJJSitYbHQwdnM1U2pXM3RJZTg5VjA3TkoweFpiUG1zUklrSW1MbjdLWWlxQTZPZkFQRlxuNGlxaGpXb0VxbFJxaU00ME5yVTVaaGUxb1BzNDg4MmZGUFZJZ1dyZ3dpS2p2TW1oVUVacktDRTN6VHpIRm1GaFxuTXJQbFBYMVZPUDdwSkZFVnpSV2J4YXRNc3VFMkx3ZWcvOE9GWkJGc0VIL3hyZ0xQb3gvenVlTWhBMEMvVnRwb1xuVk4yN3N6RmpVUmdtMnlwd3hLUER2K0NFdVVSRWNhc2NuWlN1Q0hncUt4b3hqaG9uZEZobVh3T0xPYWNua2VQVFxucHkzT0pZNHIyMUcvOU1wN1Fhc29FdWJlN0dZSUVHS2Jza0xsT2FkeWtRS0JnUUQ3NVhKUDNtMkRDQWpoaVpOa1xuZDlHeUsvc0FiR1dodHlkbnQxdjl1bWdQcVExSXNob29xTkZRSVptb3Q1RHFjWHI2bDViZ1JvY3BobEJyTWU0clxuZ09uQVkxR29lNUh6VmZLSzd6YWl0ZHZrQ0w0bUZ5R2dBeDF6bVJpWUc0RVFCN1VuYm1JWDBEamV5UU1ndmU0d1xudjRCR2VuWW81cCttYlZqYmRuTUh4MWJLVndLQmdRRGF2TExyM1NiU1ZLRE9QdWkyUmRLTzJ5WW9oZEFqbVRSblxuRFF6Y3hWTmFMRkVOZjhuZWhnSFRNRnRIMjJsR01zMUpsWUVURG1TNGcyQlkvcHh2RGxQK0JEQUs1NGgvTUZnN1xuVEY3UjdkdTd5RE5rY1lwQUVwUndxam1vY3U5dW9WYkNmZmRBOGdSTHJ1Zk5HQXI3U2FLQVZUMnRIU3Z3TUtoVFxucTRsa1JkOWxlUUtCZ1FEMFVSWnZxeEJTejk4MVNlZmd3V3B0UXNVZ0l3THNPQ1lMTGNnV3I4OHdxVW9PSVZqSFxuNlAyam1BTmVwalR6ZjMvRlFIU0JNVWIxbXFXell1bGIzcHhhRXJyb0RIcUNKdkdwbWQxSE1kYUdOWWlnanp6NFxuNHl5VXE0QTBRREVOS0R5bmN5UEhieGxKMUhPcE5TNnFyNE11V0tiMGdzT0VlM2g4M0VVejNUOHIzd0tCZ1FDcVxuajkrNG1hN3U3WHlpdGwxQjNEb3VtT1l0VFFLYTc1ajBEaklNYUhYeFo0TWdVcGYzdzJqaTQ5OFpyaFp0M2FsRVxuQ3FjbVU5L29ITkZoTTlDSnJKT0VESUhsenRzWnlublNhMSs0dEkvVjRtdG9sRHhvYjlSOThjNlo4bDJQVjVGT1xuZ1dqdXBJa0M5dlRiY1RjRzhVa2NadHQ4QkxaQlNiL0gxb2VBd0JkTmlRS0JnUURvZVAxWUgvTjl3NkM3YXRXTVxuYlRIS0hjaitxV2t1a1J1NTJDTDdsTEQ0OEUraWl2QVpQMnpkVFRhSDQwbWl1ZWIrRmVqTG1XV3lqdUFsNFFPTVxuRndDSUZDS2l6dXRHMGtWTG5uMHpNMzBGK0MySjhQQURRWFBYc1VtWStEd0FlaWF5SUxxNTBwNktuRWlWbUhocFxuWjBCLzRLZVhnalkxNVdkNVNCUnZVcVEzQlE9PVxuLS0tLS1FTkQgUFJJVkFURSBLRVktLS0tLVxuIiwKICAiY2xpZW50X2VtYWlsIjogImZpcmViYXNlLWFkbWluc2RrLTl2Nm92QGphdmEtdW4tZXBzaS1iMy5pYW0uZ3NlcnZpY2VhY2NvdW50LmNvbSIsCiAgImNsaWVudF9pZCI6ICIxMDEyMzg1OTc4NjkyMTU4Mzc0OTIiLAogICJhdXRoX3VyaSI6ICJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20vby9vYXV0aDIvYXV0aCIsCiAgInRva2VuX3VyaSI6ICJodHRwczovL29hdXRoMi5nb29nbGVhcGlzLmNvbS90b2tlbiIsCiAgImF1dGhfcHJvdmlkZXJfeDUwOV9jZXJ0X3VybCI6ICJodHRwczovL3d3dy5nb29nbGVhcGlzLmNvbS9vYXV0aDIvdjEvY2VydHMiLAogICJjbGllbnRfeDUwOV9jZXJ0X3VybCI6ICJodHRwczovL3d3dy5nb29nbGVhcGlzLmNvbS9yb2JvdC92MS9tZXRhZGF0YS94NTA5L2ZpcmViYXNlLWFkbWluc2RrLTl2Nm92JTQwamF2YS11bi1lcHNpLWIzLmlhbS5nc2VydmljZWFjY291bnQuY29tIgp9";

	
	private FirebaseUtil instance;
	
	public FirebaseUtil getInstance() {
		if (instance == null) {
			instance = new FirebaseUtil();
			try {
				instance.init();
			} catch (TechnicalException e) {
				// FAIL
				e.printStackTrace();
			}
		}
		return instance;
	}
	
	public DatabaseReference firebaseDatabase() {
		DatabaseReference firebase = FirebaseDatabase.getInstance().getReference();
		return firebase;
	}
	
	private void init() throws TechnicalException {
		try {

			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(
							GoogleCredentials.fromStream(firebaseKey()))
					.setDatabaseUrl("https://java-un-epsi-b3.firebaseio.com").build();
			  if(FirebaseApp.getApps().isEmpty()) { 
					FirebaseApp.initializeApp(options);
	            }
		} catch (FileNotFoundException e) {

			TechnicalException.throwTechnicalException("key file not found", e);
		} catch (IOException e) {
			TechnicalException.throwTechnicalException("io exception", e);
		}
	}
	
	private InputStream firebaseKey ( ) throws IOException {
		byte[] decode = Base64.getDecoder().decode(KEY_FILE);
		return  new ByteArrayInputStream(decode);
		
	}
	
}
