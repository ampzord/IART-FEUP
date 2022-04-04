FEUP-IART
============
Repository for Artificial Intelligence class @ FEUP.

# Project - Optimization in a Conference Scheduling

## Objective:

Application of optimization algorithms to the problem of scheduling a conference.

## Description:

In the scheduling of a scientific conference, there is a set of presentations of scientific articles (papers) submitted and accepted in the conference. A paper covers one or more themes. Each presentation has a speaker (one of the authors of the paper) and may have different duration depending on whether it corresponds to a full-paper (30 minutes) or short-paper (20 minutes).

Presentations are grouped in thematic sessions. Each session has a maximum duration of 2 hours. Papers presented in the same session should be related to the theme of the session. There may be a maximum of M sessions in parallel, related to the number of rooms available. The conference will take place over 3 days, and there will be a maximum of 4 sessions each day, with coffee breaks or lunch breaks.

It is intended to set up the conference program, seeking to ensure balanced sessions: each session must have at least 2 full-paper presentations. Note that each presenter can be a co-author (and presenter) of more than one paper, so it is necessary to ensure that there are no parallel sessions with presenters in common.

The work consists of applying optimization methodologies (Genetic Algorithms) to this scenario, solving instances of considerable dimensions. These methodologies can be used to perform a comparative analysis of the performance level of each.

## Execution:

Run _Conference.jar_ file.

## License

This project is licensed under the terms of the **MIT** [license](https://github.com/ampzord/FEUP-IART/blob/master/LICENSE).
