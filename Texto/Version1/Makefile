pdf::
	pdflatex Livro 
	makeindex Livro.idx -s style.ist
	bibtex Livro
	pdflatex Livro
	pdflatex Livro

clean::
	rm -f bib/*~ fig/*~ tex/*~ *~ *.lof *.lol *.lot *.bak *.bbl *.blg *.dvi *.log *.aux *.ps *.idx *.ilg *.ind *.ptc *.toc *.out
