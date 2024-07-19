package com.garbuz.messaging.model;

import java.util.Objects;

public class Message {

	private String text;
	private boolean sent;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isSent() {
		return sent;
	}

	public void setSent(boolean sent) {
		this.sent = sent;
	}

	@Override
	public int hashCode() {
		return Objects.hash(sent, text);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		return sent == other.sent && Objects.equals(text, other.text);
	}

	@Override
	public String toString() {
		return "Message [text=" + text + ", sent=" + sent + "]";
	}


}
