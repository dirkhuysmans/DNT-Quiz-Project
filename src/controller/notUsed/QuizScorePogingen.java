package controller.notUsed;

import controller.IQuizScore;
import model.OpdrachtAntwoord;
import model.Quizdeelname;

public class QuizScorePogingen implements IQuizScore {

	@Override
	public double berekenQuizScore(Quizdeelname qd) {
		// TODO Auto-generated method stub
		double score = 0;
		
		for (OpdrachtAntwoord oa : qd.getListOpdrachtAntwoord()) {
			if(oa.getQuizOpdracht().getOpdracht().isJuisteAntwoord(oa.getLaatsteAntwoord()) && oa.getAantalPogingen()==1){
				score += oa.getQuizOpdracht().getMaxScore();
			}
			else if(oa.getQuizOpdracht().getOpdracht().isJuisteAntwoord(oa.getLaatsteAntwoord()) && oa.getAantalPogingen()<= oa.getQuizOpdracht().getOpdracht().getMaxAantalPogingen()){
				score += oa.getQuizOpdracht().getMaxScore()/2;
			}
		
		}
		return score;

	}
}
