
package tn.esprit.spring.services;

import tn.esprit.spring.entities.Profession;

public interface IProfessionService
	{
	
		public void AddProfession (Profession  Profession );
		public void UpdateProfession (int ID_Profession,String  ProfessionName);
		public void DeleteProfession (int ID_Profession);
	}
