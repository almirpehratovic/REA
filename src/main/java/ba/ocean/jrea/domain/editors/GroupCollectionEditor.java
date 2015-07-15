package ba.ocean.jrea.domain.editors;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;

import ba.ocean.jrea.domain.structure.Group;
import ba.ocean.pizzeria.service.PizzeriaService;

public class GroupCollectionEditor extends CustomCollectionEditor{
	
	private PizzeriaService pizzeriaService;
	
	public GroupCollectionEditor(PizzeriaService pizzeriaService) {
		super(List.class);
		this.pizzeriaService = pizzeriaService;
	}

	@Override
	protected Object convertElement(Object element) {
		try {
			int id = Integer.parseInt(element+"");
			Group group = pizzeriaService.findGroupById(id);
			return group;
		} catch (NumberFormatException e) {
			
		}
		return null;
	}
	
}
