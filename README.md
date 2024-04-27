<changeSet id="FN_SU_MAKER_DATE_UPD" author="HariTeja.B">
		<sql splitStatements="false">
			<![CDATA[
			    create or replace FUNCTION FN_SU_MAKER_DATE (v_usrID IN NUMBER) RETURN DATE AS v_lastMntON Date;
				BEGIN
					Select LastMntOn into v_lastMntON From AdtSecUsers Where UsrId = v_usrID and AuditImage In( 'W', 'A') and RecordStatus = 'Submitted' and Rownum = 1 order by AuditID desc;
				RETURN v_lastMntON;
				END FN_SU_MAKER_DATE;
            	]]>
		</sql>
	</changeSet>
 
	<changeSet id="FN_SU_OLD_PROFILE" author="hemusai.v">
		<sql splitStatements="false">
			<![CDATA[
				create or replace FUNCTION FN_SU_OLD_PROFILE(p_UsrId IN NUMBER)
				RETURN VARCHAR2
				AS
				v_Profile VARCHAR2(4000 CHAR);
				BEGIN
					v_Profile := '';
 
					SELECT OprCode INTO v_Profile
					FROM (
						SELECT so.OprCode
						FROM SecOperations so
						INNER JOIN AdtSecUserOperations asuo ON so.OprId = asuo.OprId
						WHERE asuo.UsrId = p_UsrId
						AND asuo.AuditImage = 'A'
						AND asuo.RecordStatus = 'Approved'
					ORDER BY asuo.AuditId DESC
					)
					WHERE ROWNUM = 1;  
				RETURN v_Profile;
				END FN_SU_OLD_PROFILE;
            ]]>
		</sql>
	</changeSet>
