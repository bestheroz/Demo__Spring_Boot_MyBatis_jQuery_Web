package com.github.bestheroz.sample.web.admin.code;

import com.github.bestheroz.sample.web.tablevo.samplecodedet.TableSampleCodeDetDAO;
import com.github.bestheroz.sample.web.tablevo.samplecodedet.TableSampleCodeDetVO;
import com.github.bestheroz.sample.web.tablevo.samplecodemst.TableSampleCodeMstDAO;
import com.github.bestheroz.sample.web.tablevo.samplecodemst.TableSampleCodeMstVO;
import com.github.bestheroz.standard.common.exception.BusinessException;
import com.github.bestheroz.standard.common.util.MapperUtils;
import com.github.bestheroz.standard.common.util.SessionUtils;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class AdminCodeService {
    @Resource PlatformTransactionManager platformTransactionManager;
    @Resource TableSampleCodeMstDAO tableSampleCodeMstDAO;
    @Resource TableSampleCodeDetDAO tableSampleCodeDetDAO;

    public List<AdminCodeVO> getSampleCodeMstVOList(final AdminCodeVO vo) {
        final TableSampleCodeMstVO tableSampleCodeMstVO = MapperUtils.toObject(vo, TableSampleCodeMstVO.class);
        final Set<String> whereKeys = new HashSet<>();
        if (StringUtils.isNotEmpty(vo.getGroupCode())) {
            whereKeys.add("groupCode");
        }
        return MapperUtils.toArrayList(this.tableSampleCodeMstDAO.getList(tableSampleCodeMstVO, whereKeys, "UPDATED DESC"), AdminCodeVO.class);
    }

    public void insertSampleCodeMst(final TableSampleCodeMstVO vo) {
        this.tableSampleCodeMstDAO.insert(vo);
        SessionUtils.removeAttribute("Code." + vo.getGroupCode());
    }

    public void updateSampleCodeMst(final TableSampleCodeMstVO vo) {
        this.tableSampleCodeMstDAO.update(vo, Collections.singleton("groupCode"), null);
        SessionUtils.removeAttribute("Code." + vo.getGroupCode());
    }

    // @Transactional
    public void deleteCOMM_CODE(final TableSampleCodeMstVO vo) {
        /*
          <pre>
          아래 PlatformTransactionManager 를 이용하면 메소드 내에 특정 부분만을 트랜잭션 처리할 수 있다.
          일반적으로는 상단에 주석되어 있는 @Transactional 을 사용하여 메소드 전체를 트랜잭션 처리한다.
          </pre>
         */
        final DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
        defaultTransactionDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        final TransactionStatus status = this.platformTransactionManager.getTransaction(defaultTransactionDefinition);

        try {
            final TableSampleCodeDetVO tableSampleCodeDetVO = MapperUtils.toObject(vo, TableSampleCodeDetVO.class);
            try {
                this.tableSampleCodeDetDAO.delete(tableSampleCodeDetVO, Collections.singleton("groupCode"));
            } catch (final BusinessException e) {
                if (!e.isNoDataSuccess()) {
                    log.warn(ExceptionUtils.getStackTrace(e));
                    throw e;
                }
            }
            final TableSampleCodeMstVO tableSampleCodeMstVO = MapperUtils.toObject(vo, TableSampleCodeMstVO.class);
            this.tableSampleCodeMstDAO.delete(tableSampleCodeMstVO, Collections.singleton("groupCode"));
            SessionUtils.removeAttribute("Code." + tableSampleCodeMstVO.getGroupCode());
            this.platformTransactionManager.commit(status);
        } catch (final BusinessException e) {
            if (!status.isCompleted()) {
                this.platformTransactionManager.rollback(status);
            }
            log.warn(ExceptionUtils.getStackTrace(e));
            throw e;
        }
    }

    // DETAIL PART
    public List<AdminCodeVO> getSampleCodeDetVOList(final AdminCodeVO vo) {
        final Set<String> whereKeys = Sets.newHashSet("groupCode");
        if (StringUtils.isNotEmpty(vo.getCode())) {
            whereKeys.add("code");
        }
        return MapperUtils.toArrayList(this.tableSampleCodeDetDAO.getList(MapperUtils.toObject(vo, TableSampleCodeDetVO.class), whereKeys, "UPDATED DESC"),
                AdminCodeVO.class);
    }

    public void insertSampleCodeDet(final TableSampleCodeDetVO vo) {


        this.tableSampleCodeDetDAO.insert(vo);
        SessionUtils.removeAttribute("Code." + vo.getGroupCode());
    }

    public void updateSampleCodeDet(final TableSampleCodeDetVO vo) {

        this.tableSampleCodeDetDAO.update(vo, ImmutableSet.of("groupCode", "code"), null);
        SessionUtils.removeAttribute("Code." + vo.getGroupCode());
    }

    public void deleteSampleCodeDet(final TableSampleCodeDetVO vo) {
        this.tableSampleCodeDetDAO.delete(vo, ImmutableSet.of("groupCode", "code"));
        SessionUtils.removeAttribute("Code." + vo.getGroupCode());
    }

}
