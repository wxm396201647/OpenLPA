/*
 * This class file was automatically generated by ASN1bean v1.13.0 (http://www.beanit.com)
 */

package com.truphone.rsp.dto.asn1.rspdefinitions;

import java.io.IOException;
import java.io.EOFException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.io.Serializable;
import com.beanit.asn1bean.ber.*;
import com.beanit.asn1bean.ber.types.*;
import com.beanit.asn1bean.ber.types.string.*;

import com.truphone.rsp.dto.asn1.pkix1explicit88.Certificate;
import com.truphone.rsp.dto.asn1.pkix1explicit88.CertificateList;
import com.truphone.rsp.dto.asn1.pkix1explicit88.Time;
import com.truphone.rsp.dto.asn1.pkix1implicit88.SubjectKeyIdentifier;

public class DisableProfileRequest implements BerType, Serializable {

	private static final long serialVersionUID = 1L;

	public static class ProfileIdentifier implements BerType, Serializable {

		private static final long serialVersionUID = 1L;

		private byte[] code = null;
		private OctetTo16 isdpAid = null;
		private Iccid iccid = null;
		
		public ProfileIdentifier() {
		}

		public ProfileIdentifier(byte[] code) {
			this.code = code;
		}

		public void setIsdpAid(OctetTo16 isdpAid) {
			this.isdpAid = isdpAid;
		}

		public OctetTo16 getIsdpAid() {
			return isdpAid;
		}

		public void setIccid(Iccid iccid) {
			this.iccid = iccid;
		}

		public Iccid getIccid() {
			return iccid;
		}

		@Override public int encode(OutputStream reverseOS) throws IOException {

			if (code != null) {
				reverseOS.write(code);
				return code.length;
			}

			int codeLength = 0;
			if (iccid != null) {
				codeLength += iccid.encode(reverseOS, true);
				return codeLength;
			}
			
			if (isdpAid != null) {
				codeLength += isdpAid.encode(reverseOS, false);
				// write tag: APPLICATION_CLASS, PRIMITIVE, 15
				reverseOS.write(0x4F);
				codeLength += 1;
				return codeLength;
			}
			
			throw new IOException("Error encoding CHOICE: No element of CHOICE was selected.");
		}

		@Override public int decode(InputStream is) throws IOException {
			return decode(is, null);
		}

		public int decode(InputStream is, BerTag berTag) throws IOException {

			int tlvByteCount = 0;
			boolean tagWasPassed = (berTag != null);

			if (berTag == null) {
				berTag = new BerTag();
				tlvByteCount += berTag.decode(is);
			}

			if (berTag.equals(BerTag.APPLICATION_CLASS, BerTag.PRIMITIVE, 15)) {
				isdpAid = new OctetTo16();
				tlvByteCount += isdpAid.decode(is, false);
				return tlvByteCount;
			}

			if (berTag.equals(Iccid.tag)) {
				iccid = new Iccid();
				tlvByteCount += iccid.decode(is, false);
				return tlvByteCount;
			}

			if (tagWasPassed) {
				return 0;
			}

			throw new IOException("Error decoding CHOICE: Tag " + berTag + " matched to no item.");
		}

		public void encodeAndSave(int encodingSizeGuess) throws IOException {
			ReverseByteArrayOutputStream reverseOS = new ReverseByteArrayOutputStream(encodingSizeGuess);
			encode(reverseOS);
			code = reverseOS.getArray();
		}

		@Override public String toString() {
			StringBuilder sb = new StringBuilder();
			appendAsString(sb, 0);
			return sb.toString();
		}

		public void appendAsString(StringBuilder sb, int indentLevel) {

			if (isdpAid != null) {
				sb.append("isdpAid: ").append(isdpAid);
				return;
			}

			if (iccid != null) {
				sb.append("iccid: ").append(iccid);
				return;
			}

			sb.append("<none>");
		}

	}

	public static final BerTag tag = new BerTag(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 50);

	private byte[] code = null;
	private ProfileIdentifier profileIdentifier = null;
	private BerBoolean refreshFlag = null;
	
	public DisableProfileRequest() {
	}

	public DisableProfileRequest(byte[] code) {
		this.code = code;
	}

	public void setProfileIdentifier(ProfileIdentifier profileIdentifier) {
		this.profileIdentifier = profileIdentifier;
	}

	public ProfileIdentifier getProfileIdentifier() {
		return profileIdentifier;
	}

	public void setRefreshFlag(BerBoolean refreshFlag) {
		this.refreshFlag = refreshFlag;
	}

	public BerBoolean getRefreshFlag() {
		return refreshFlag;
	}

	@Override public int encode(OutputStream reverseOS) throws IOException {
		return encode(reverseOS, true);
	}

	public int encode(OutputStream reverseOS, boolean withTag) throws IOException {

		if (code != null) {
			reverseOS.write(code);
			if (withTag) {
				return tag.encode(reverseOS) + code.length;
			}
			return code.length;
		}

		int codeLength = 0;
		int sublength;

		codeLength += refreshFlag.encode(reverseOS, false);
		// write tag: CONTEXT_CLASS, PRIMITIVE, 1
		reverseOS.write(0x81);
		codeLength += 1;
		
		sublength = profileIdentifier.encode(reverseOS);
		codeLength += sublength;
		codeLength += BerLength.encodeLength(reverseOS, sublength);
		// write tag: CONTEXT_CLASS, CONSTRUCTED, 0
		reverseOS.write(0xA0);
		codeLength += 1;
		
		codeLength += BerLength.encodeLength(reverseOS, codeLength);

		if (withTag) {
			codeLength += tag.encode(reverseOS);
		}

		return codeLength;

	}

	@Override public int decode(InputStream is) throws IOException {
		return decode(is, true);
	}

	public int decode(InputStream is, boolean withTag) throws IOException {
		int tlByteCount = 0;
		int vByteCount = 0;
		BerTag berTag = new BerTag();

		if (withTag) {
			tlByteCount += tag.decodeAndCheck(is);
		}

		BerLength length = new BerLength();
		tlByteCount += length.decode(is);
		int lengthVal = length.val;
		vByteCount += berTag.decode(is);

		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.CONSTRUCTED, 0)) {
			vByteCount += length.decode(is);
			profileIdentifier = new ProfileIdentifier();
			vByteCount += profileIdentifier.decode(is, null);
			vByteCount += length.readEocIfIndefinite(is);
			vByteCount += berTag.decode(is);
		}
		else {
			throw new IOException("Tag does not match mandatory sequence component.");
		}
		
		if (berTag.equals(BerTag.CONTEXT_CLASS, BerTag.PRIMITIVE, 1)) {
			refreshFlag = new BerBoolean();
			vByteCount += refreshFlag.decode(is, false);
			if (lengthVal >= 0 && vByteCount == lengthVal) {
				return tlByteCount + vByteCount;
			}
			vByteCount += berTag.decode(is);
		}
		else {
			throw new IOException("Tag does not match mandatory sequence component.");
		}
		
		if (lengthVal < 0) {
			while (!berTag.equals(0, 0, 0)) {
				vByteCount += DecodeUtil.decodeUnknownComponent(is);
				vByteCount += berTag.decode(is);
			}
			vByteCount += BerLength.readEocByte(is);
			return tlByteCount + vByteCount;
		} else {
			while (vByteCount < lengthVal) {
				vByteCount += DecodeUtil.decodeUnknownComponent(is);
				if (vByteCount == lengthVal) {
					return tlByteCount + vByteCount;
				}
				vByteCount += berTag.decode(is);
			}
		}
		throw new IOException("Unexpected end of sequence, length tag: " + lengthVal + ", bytes decoded: " + vByteCount);
	}

	public void encodeAndSave(int encodingSizeGuess) throws IOException {
		ReverseByteArrayOutputStream reverseOS = new ReverseByteArrayOutputStream(encodingSizeGuess);
		encode(reverseOS, false);
		code = reverseOS.getArray();
	}

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		appendAsString(sb, 0);
		return sb.toString();
	}

	public void appendAsString(StringBuilder sb, int indentLevel) {

		sb.append("{");
		sb.append("\n");
		for (int i = 0; i < indentLevel + 1; i++) {
			sb.append("\t");
		}
		if (profileIdentifier != null) {
			sb.append("profileIdentifier: ");
			profileIdentifier.appendAsString(sb, indentLevel + 1);
		}
		else {
			sb.append("profileIdentifier: <empty-required-field>");
		}
		
		sb.append(",\n");
		for (int i = 0; i < indentLevel + 1; i++) {
			sb.append("\t");
		}
		if (refreshFlag != null) {
			sb.append("refreshFlag: ").append(refreshFlag);
		}
		else {
			sb.append("refreshFlag: <empty-required-field>");
		}
		
		sb.append("\n");
		for (int i = 0; i < indentLevel; i++) {
			sb.append("\t");
		}
		sb.append("}");
	}

}

