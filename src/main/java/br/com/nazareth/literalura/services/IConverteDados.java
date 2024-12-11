package br.com.nazareth.literalura.services;

public interface IConverteDados {
    <T> T  obterDados(String json, Class<T> classe);
}
