The program is currently ran by using the run configurations on UMLConversionApp.
In the run configurations, you enter in classes you want to turn into PlantUML, the
method of parsing you want to use, and whichever alterations you wish to apply to
the final PlantUML code. If you want to change the folder that you want to select
the classes from, that is also done in the run configurations menu. When ran, 
UMLConversionApp defaults to using NormalParser, but one can change the parser by
putting in a keyname beginning with "-p", ex: "-pRecursive". If multiple parsers are
delivered in the input, the UMLConversionApp will simply chose the last one and
disregard all others. UMLConversionApp defaults to using no Alterations, but one can add
any number of Alterations by putting in a keyname beginning with "-a", ex: "-aPublic". 

Cambron Johnson: Took the lead on working on UMLConversionApp.
	Did most of the setup through Git Lab and setting up the libraries.

Olivia Penry: Took the lead on working on the Alteration and tis subclasses.
	Did most of the work to figure out how to print the ClassNodes.

Tyson Clark: Took the lead on working on Parser and its subclasses.
	Created this README file.

All: Worked on UMLConverter to properly translate from ClassNode to PlantUML
	Worked on creating the data structures that will make future work easier.