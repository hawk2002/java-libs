package org.openehr.am.serialize;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openehr.am.archetype.Archetype;
import org.openehr.am.archetype.assertion.Assertion;
import org.openehr.am.archetype.constraintmodel.CComplexObject;
import org.openehr.am.archetype.ontology.ArchetypeOntology;
import org.openehr.am.archetype.ontology.DefinitionItem;
import org.openehr.am.archetype.ontology.OntologyBinding;
import org.openehr.am.archetype.ontology.OntologyBindingItem;
import org.openehr.am.archetype.ontology.OntologyDefinitions;
import org.openehr.am.archetype.ontology.Query;
import org.openehr.am.archetype.ontology.QueryBindingItem;
import org.openehr.am.archetype.ontology.TermBindingItem;
import org.openehr.rm.common.generic.RevisionHistory;
import org.openehr.rm.common.resource.ResourceDescription;
import org.openehr.rm.common.resource.TranslationDetails;
import org.openehr.rm.datatypes.text.CodePhrase;
import org.openehr.rm.support.terminology.TerminologyService;

/**
 * Testcase that verifies print out of a simple archetype
 * 
 * @author Rong.Chen
 * 
 */
public class SimpleArchetypeTest extends SerializerTestBase {

	public SimpleArchetypeTest(String test) {
		super(test);
	}

	public void testPrintArchetypeLanguagePart() throws Exception {
		String adlVersion = "1.4";
		String id = "adl-test-ENTRY.most_minimal.draft";
		String parentId = null;
		String concept = "at0000";
		CodePhrase originalLanguage = new CodePhrase("iso639-2", "en");

		Map<String, TranslationDetails> translations = null;
		ResourceDescription description = null;
		RevisionHistory revisionHistory = null;
		boolean isControlled = false;
		CComplexObject definition = new CComplexObject("/", "ENTRY", null,
				"at0000", null, null);

		DefinitionItem item = new DefinitionItem("at0000", "most minimal",
				"most minimal");
		List<DefinitionItem> items = new ArrayList<DefinitionItem>();
		items.add(item);
		OntologyDefinitions definitions = new OntologyDefinitions("en", items);
		List<OntologyDefinitions> termDefinitionsList = 
			new ArrayList<OntologyDefinitions>();
		termDefinitionsList.add(definitions);

		List<OntologyDefinitions> constraintDefinitionsList = null;
		List<String> languages = new ArrayList<String>();
		languages.add("en");
		List<String> terminologies = null;
		List<OntologyBinding> termBindingList = null;
		List<OntologyBinding> constraintBindingList = null;

		ArchetypeOntology ontology = new ArchetypeOntology("en", languages,
				terminologies, termDefinitionsList, constraintDefinitionsList,
				termBindingList, constraintBindingList);

		Set<Assertion> invariants = null;
		TerminologyService terminologyService = null;

		Archetype archetype = new Archetype(adlVersion, id, parentId, concept,
				originalLanguage, translations, description, revisionHistory,
				isControlled, definition, ontology, invariants,
				terminologyService);

		clean();
		outputter.output(archetype, out);
		verifyByFile("adl-test-entry.most_minimal.test.adl");					  					
	}
}