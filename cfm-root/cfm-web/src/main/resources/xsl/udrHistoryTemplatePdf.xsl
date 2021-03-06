<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format"
	exclude-result-prefixes="fo">
	<xsl:output method="xml" version="1.0" omit-xml-declaration="no"
		indent="yes" />


	<!-- VARIABLES -->
	<xsl:variable name="msisdnLabel" select="'RECPT_MSISDN'" />
	<xsl:variable name="startTimeLabel" select="'STARTTIME'" />
	<xsl:variable name="startDateLabel" select="'STARTDATE'" />
	<xsl:variable name="typeLabel" select="'TYPE'" />
	<xsl:variable name="durationLabel" select="'DURATION'" />
	<xsl:variable name="costLabel" select="'COST'" />
	<xsl:variable name="uplinkLabel" select="'UPLINK'" />
	<xsl:variable name="downlinkLabel" select="'DOWNLINK'" />


	<xsl:variable name="typeConnect" select="'ROZMOWY'" />
	<xsl:variable name="typeSMS" select="'SMS'" />
	<xsl:variable name="typeMMS" select="'MMS'" />
	<xsl:variable name="typeData" select="'DANE'" />
	<xsl:variable name="typeDataSend" select="'DANE WYSŁANE'" />
	<xsl:variable name="typeDataReceived" select="'DANE POBRANE'" />
	<xsl:variable name="typeServices" select="'SERVICES'" />
	<xsl:variable name="typeRecharge" select="'DOŁADOWANIA'" />
	<xsl:variable name="typePayment" select="'OBCIĄŻENIA'" />
	<xsl:variable name="typeOmnix" select="'OMNIX'" />
	<xsl:variable name="typeOthers" select="'INNE'" />

	<xsl:variable name="labelConnect" select="'Połączenia'" />
	<xsl:variable name="labelSMS" select="'SMS'" />
	<xsl:variable name="labelMMS" select="'MMS'" />
	<xsl:variable name="labelInternet" select="'Dane'" />
	<xsl:variable name="labelInternetSend" select="'Dane wysłane'" />
	<xsl:variable name="labelInternetReceived" select="'Date odebrane'" />
	<xsl:variable name="labelServices" select="'Pakiety i sługi'" />
	<xsl:variable name="labelRecharge" select="'Doładowanie'" />
	<xsl:variable name="labelPayment" select="'Obiążenie'" />
	<xsl:variable name="labelOmnix" select="'Omnix'" />
	<xsl:variable name="labelOthers" select="'Inne'" />


	<xsl:attribute-set name="historyBorder">
		<xsl:attribute name="border">dotted 0.5mm #E10074</xsl:attribute>
	</xsl:attribute-set>
	<!-- VARIABLES END -->

	<!-- ========================= -->
	<!-- root element -->
	<!-- ========================= -->
	<xsl:template match="root">
		<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format" color="#E10074"
			font-family="Arial">
			<fo:layout-master-set>
				<fo:simple-page-master master-name="simpleA4"
					page-height="29.7cm" page-width="21cm" margin-top="2cm"
					margin-bottom="2cm" margin-left="2cm" margin-right="2cm">
					<fo:region-body />
				</fo:simple-page-master>
			</fo:layout-master-set>


			<fo:page-sequence master-reference="simpleA4">
				<fo:flow flow-name="xsl-region-body">

					<!-- TITLE -->
					<fo:block font-size="16pt" font-weight="bold" space-after="5mm">
						<!-- <fo:external-graphic src="url(/images/phone.jpg)"/> -->
						Historia konta
					</fo:block>
					<fo:block font-size="12pt" font-weight="normal"
						space-after="5mm">Wykaz bieżących
						połączeń i opłat
					</fo:block>


					<!-- TABLE -->
					<fo:block font-size="10pt" color="#000000">
						<fo:table table-layout="fixed">

							<fo:table-column column-width="28mm" />
							<fo:table-column column-width="28mm" />
							<fo:table-column column-width="28mm" />
							<fo:table-column column-width="28mm" />
							<fo:table-column column-width="28mm" />
							<fo:table-column column-width="28mm" />
							<fo:table-body>

								<!-- first row -->
								<fo:table-row height="10mm" text-align="center"
									font-weight="bold">
									<fo:table-cell xsl:use-attribute-sets="historyBorder"
										display-align="center">
										<fo:block>Data</fo:block>
									</fo:table-cell>
									<fo:table-cell xsl:use-attribute-sets="historyBorder"
										display-align="center">
										<fo:block>Godzina</fo:block>
									</fo:table-cell>
									<fo:table-cell xsl:use-attribute-sets="historyBorder"
										display-align="center">
										<fo:block>Typ</fo:block>
									</fo:table-cell>
									<fo:table-cell xsl:use-attribute-sets="historyBorder"
										display-align="center">
										<fo:block>Numer</fo:block>
									</fo:table-cell>
									<fo:table-cell xsl:use-attribute-sets="historyBorder"
										display-align="center">
										<fo:block>Czas trwania</fo:block>
									</fo:table-cell>
									<fo:table-cell xsl:use-attribute-sets="historyBorder"
										display-align="center">
										<fo:block>Koszt brutto</fo:block>
									</fo:table-cell>
								</fo:table-row>
								<!-- first row end -->

								<xsl:for-each select="row">
									<xsl:if
										test="contains(concat($typeConnect,$typeSMS,$typeMMS,$typeData,$typeDataSend,$typeDataReceived,$typeOmnix,$typeRecharge,$typePayment,$typeOthers),col[@name=$typeLabel])">
										<fo:table-row height="10mm" text-align="center">
											<fo:table-cell xsl:use-attribute-sets="historyBorder"
												display-align="center">
												<fo:block>
													<xsl:if test="boolean(col[@name=$startTimeLabel]/text())">
														<!-- <xsl:value-of select="concat(substring(col[@name=$startTimeLabel], 
															1, 4), '.', substring(col[@name=$startTimeLabel], 6, 2), '.', substring(col[@name=$startTimeLabel], 
															9, 2))"/> -->
														<xsl:variable name="startDate"
															select="col[@name=$startDateLabel]"></xsl:variable>
															<xsl:variable name="startDateReplaced" select="$startDate"/>
														<xsl:value-of select="$startDateReplaced" />
													</xsl:if>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell xsl:use-attribute-sets="historyBorder"
												display-align="center">
												<fo:block>
													<xsl:if test="boolean(col[@name=$startTimeLabel]/text())">
														<xsl:value-of select="col[@name=$startTimeLabel]" />
														<!-- <xsl:value-of select="concat(substring(col[@name=$startTimeLabel], 
															12, 2), ':', substring(col[@name=$startTimeLabel], 15, 2))"/> -->
													</xsl:if>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell xsl:use-attribute-sets="historyBorder"
												display-align="center">
												<fo:block>
													<xsl:if test="col[@name=$typeLabel]=$typeConnect">
														<xsl:value-of select="$labelConnect" />
													</xsl:if>
													<xsl:if test="col[@name=$typeLabel]=$typeSMS">
														<xsl:value-of select="$labelSMS" />
													</xsl:if>
													<xsl:if test="col[@name=$typeLabel]=$typeMMS">
														<xsl:value-of select="$labelMMS" />
													</xsl:if>
													<xsl:if test="col[@name=$typeLabel]=$typeData">
														<xsl:value-of select="$labelInternet" />
													</xsl:if>
													<xsl:if test="col[@name=$typeLabel]=$typeDataSend">
														<xsl:value-of select="$labelInternetSend" />
													</xsl:if>
													<xsl:if test="col[@name=$typeLabel]=$typeDataReceived">
														<xsl:value-of select="$labelInternetReceived" />
													</xsl:if>
													<xsl:if test="col[@name=$typeLabel]=$typeServices">
														<xsl:value-of select="$labelServices" />
													</xsl:if>
													<xsl:if test="col[@name=$typeLabel]=$typeRecharge">
														<xsl:value-of select="$labelRecharge" />
													</xsl:if>
													<xsl:if test="col[@name=$typeLabel]=$typePayment">
														<xsl:value-of select="$labelPayment" />
													</xsl:if>
													<xsl:if test="col[@name=$typeLabel]=$typeOmnix">
														<xsl:value-of select="$labelOmnix" />
													</xsl:if>
													<xsl:if test="col[@name=$typeLabel]=$typeOthers">
														<xsl:value-of select="$labelOthers" />
													</xsl:if>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell xsl:use-attribute-sets="historyBorder"
												display-align="center">
												<fo:block>
													<xsl:value-of select="col[@name=$msisdnLabel]" />
												</fo:block>
											</fo:table-cell>
											<fo:table-cell xsl:use-attribute-sets="historyBorder"
												display-align="center">
												<fo:block>
													<!-- <xsl:value-of select="col[@name=$durationLabel]" /> -->
													<!-- value -->
													<xsl:choose>
														<xsl:when test="col[@name=$typeLabel]=$typeData">
															<xsl:value-of select="col[@name=$downlinkLabel]" />
														</xsl:when>
														<xsl:when test="col[@name=$typeLabel]=$typeDataSend">
															<xsl:value-of select="col[@name=$uplinkLabel]" />
														</xsl:when>
														<xsl:when test="col[@name=$typeLabel]=$typeDataReceived">
															<xsl:value-of select="col[@name=$downlinkLabel]" />
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="col[@name=$durationLabel]" />
														</xsl:otherwise>
													</xsl:choose>

													<!-- unit -->
													<xsl:if test="col[@name=$typeLabel]=$typeData">
														MB
													</xsl:if>
													<xsl:if test="col[@name=$typeLabel]=$typeDataSend">
														MB
													</xsl:if>
													<xsl:if test="col[@name=$typeLabel]=$typeDataReceived">
														MB
													</xsl:if>
												</fo:block>
											</fo:table-cell>
											<fo:table-cell xsl:use-attribute-sets="historyBorder"
												display-align="center">
												<fo:block>
													<xsl:variable name="costValue"
														select="number(col[@name=$costLabel])" />
													<xsl:if test="string($costValue)!='NaN'">
														<xsl:if test="$costValue &lt; 0">
															<xsl:value-of select="0  -$costValue" />
														</xsl:if>
														<xsl:if test="$costValue &gt; 0">
															<xsl:value-of select="$costValue" />
														</xsl:if>
														<xsl:if test="number(col[@name=$costLabel]) = 0">
															<xsl:value-of select="$costValue" />
														</xsl:if>
														zł
													</xsl:if>
												</fo:block>
											</fo:table-cell>
										</fo:table-row>
									</xsl:if>
								</xsl:for-each>
							</fo:table-body>
						</fo:table>
					</fo:block>
				</fo:flow>
			</fo:page-sequence>

		</fo:root>
	</xsl:template>
</xsl:stylesheet>