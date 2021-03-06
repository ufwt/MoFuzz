package de.hub.mse.emf.serge.benchmarks;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.Diagnostician;

import org.junit.runner.RunWith;

import com.pholser.junit.quickcheck.From;

import edu.berkeley.cs.jqf.fuzz.Fuzz;

import de.hub.mse.emf.fuzz.JQFModelFuzzer;

import de.hub.mse.emf.generator.cgf.GuidedSERGeUMLGenerator;

@RunWith(JQFModelFuzzer.class)
public class EcoreUtilsTest {

    @Fuzz
    public void completeTest(@From(GuidedSERGeUMLGenerator.class) Resource resource) {
    	EObject root = resource.getContents().get(0);
		Diagnostic diagnostic = Diagnostician.INSTANCE.validate(root);
		EcoreUtil.computeDiagnostic(resource, true);
		
		for(TreeIterator<EObject> iter = resource.getAllContents(); iter.hasNext();) {
			EObject eobj = iter.next();
			EcoreUtil.getID(eobj);
			EcoreUtil.getIdentification(eobj);
			EcoreUtil.getRootContainer(eobj);
			EcoreUtil.getURI(eobj);
			EcoreUtil.isAncestor(resource.getContents(), eobj);
		}
		
		EObject copy = EcoreUtil.copy(root);
		//EcoreUtil.delete(EcoreUtil.copyAll(resource.getContents()), true);
		
		EcoreUtil.filterDescendants(EcoreUtil.copyAll(resource.getContents()));
		
		EcoreUtil.getAllContents(root, true);
		EcoreUtil.getAllProperContents(root, true);
		


		EcoreUtil.delete(copy, true);
    }
    
}

