# Layout Manager Notes

looked at https://docs.oracle.com/javase/tutorial/uiswing/layout/howLayoutWorks.html

Layout managers basically do two things:
1. Calculate the minimum/preferred/maximum sizes for a container.
2. Lay out the container's children.

This impresses me as being primarily a parent-first, child-second orientation

Layout managers do this based on the provided constraints, the container's properties (such as insets) 
and on the children's minimum/preferred/maximum sizes. 
If a child is itself a container then its own layout manger is used to get its minimum/preferred/maximum sizes and to lay it out.


HOWEVER it says that...
The end result is that to determine the best size for the container,
the system determines the sizes of the containers at the bottom of the containment hierarchy. 
These sizes then percolate up the containment hierarchy, eventually determining the container's total size.

This sounds like child-up...
So why doesn't it seem to behave like that?