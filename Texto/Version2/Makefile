pdf::
	pdflatex Livro 
	makeindex Livro.idx -s style.ist
	cp bib/Livro.bbl .
	pdflatex Livro
	pdflatex Livro

clean::
	rm -f bib/*~ fig/*~ tex/*~ *~ *.lof *.lol *.lot *.bak *.bbl *.blg *.dvi *.log *.aux *.ps *.idx *.ilg *.ind *.ptc *.toc *.out
