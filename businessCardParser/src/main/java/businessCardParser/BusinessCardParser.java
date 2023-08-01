package businessCardParser;


public class BusinessCardParser {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BusinessCardInterface newInstance = new BusinessCardInterface();
		
		newInstance.setSize(400,250);
		newInstance.setResizable(false);
		newInstance.setVisible(true);
		
	}
	
	public static String getContactInfo(String businessCard) {
		
		String[] lines = businessCard.split("\n");
		String name = "";
		String email = "";
		String phoneNumber = "";
		
		for (int i = 0; i < lines.length; i += 1) {
			
			if (phoneNumber == "") {
			
				phoneNumber = ContactInfo.getPhoneNumber(lines[i]);
			
			}
			
			if (phoneNumber != lines[i] && email == "") {
				
				email = ContactInfo.getEmailAddress(lines[i]);
				
			}
			
			if (phoneNumber != lines[i] && email != lines[i] && name == "") {
				
				name = ContactInfo.getName(lines[i]);
				
			}
			
		}
		
		return name + "\n" + phoneNumber + "\n" + email + "\n";
	}
	
	public static String sendInformation(String name, String email, String phoneNumber) {
		
		
		return "Name: " + name + "\n" + "Phone: " + phoneNumber + "\n" + "e-Mail: " + email + "\n";
		
	}
}
