package BSRG.BSBS.service;

import BSRG.BSBS.domain.entity.Problem;
import BSRG.BSBS.repository.ProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProblemService {

    private final ProblemRepository problemRepository;

    public void save(Problem problem) {
        problemRepository.save(problem);
    }
}
