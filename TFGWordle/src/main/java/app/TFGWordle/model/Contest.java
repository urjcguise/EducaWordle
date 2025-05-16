package app.TFGWordle.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class Contest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Date startDate;
    private Date endDate;
    private int numTries;
    private Boolean useDictionary;
    private Boolean useExternalFile;
    private Boolean randomMode;
    private Boolean accentMode;

    @ManyToOne
    @JoinColumn(name = "competition_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Competition competition;

    @ManyToMany
    @JoinTable(name = "contest_wordle", joinColumns = @JoinColumn(name = "contest_id"), inverseJoinColumns = @JoinColumn(name = "wordle_id"))
    @OrderColumn(name = "wordle_order")
    private List<Wordle> wordles = new ArrayList<>();

    @Convert(converter = ListOfIntegerConverter.class)
    @Column(name = "wordles_length", columnDefinition = "TEXT")
    private List<Integer> wordlesLength = new ArrayList<>();

    public Contest() {
    }

    public Contest(String name, Competition competition, Date startDate, Date endDate, int numTries,
            Boolean useDictionary, Boolean useExternalFile, Boolean randomMode, Boolean accentMode) {
        this.name = name;
        this.competition = competition;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numTries = numTries;
        this.useDictionary = useDictionary;
        this.useExternalFile = useExternalFile;
        this.randomMode = randomMode;
        this.accentMode = accentMode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Contest contest = (Contest) o;
        return Objects.equals(id, contest.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, startDate, endDate, numTries, useDictionary, useExternalFile, randomMode,
                accentMode, competition, wordles, wordlesLength);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getContestName() {
        return name;
    }

    public void setContestName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getNumTries() {
        return numTries;
    }

    public void setNumTries(int numTries) {
        this.numTries = numTries;
    }

    public Boolean getUseDictionary() {
        return useDictionary;
    }

    public void setUseDictionary(Boolean useDictionary) {
        this.useDictionary = useDictionary;
    }

    public Boolean getUseExternalFile() {
        return useExternalFile;
    }

    public void setUseExternalFile(Boolean useExternalFile) {
        this.useExternalFile = useExternalFile;
    }

    public Boolean getRandomMode() {
        return randomMode;
    }

    public void setRandomMode(Boolean randomMode) {
        this.randomMode = randomMode;
    }

    public Boolean getAccentMode() {
        return accentMode;
    }

    public void setAccentMode(Boolean accentMode) {
        this.accentMode = accentMode;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public List<Wordle> getWordles() {
        return wordles;
    }

    public void setWordles(List<Wordle> wordles) {
        this.wordles = wordles;
    }

    public void updateWordles(List<Wordle> wordles) {
        this.wordles.clear();
        this.wordles.addAll(wordles);
    }

    public List<Integer> getWordlesLength() {
        return wordlesLength;
    }

    public void setWordlesLength(List<Integer> wordlesLength) {
        this.wordlesLength = wordlesLength;
    }

    public void calculateWordlesLength() {
        this.wordlesLength.clear();
        this.wordlesLength = this.wordles.stream()
                .map(w -> w.getWord().length())
                .collect(Collectors.toList());
    }

    @Converter
    public static class ListOfIntegerConverter implements AttributeConverter<List<Integer>, String> {

        private final ObjectMapper objectMapper = new ObjectMapper();

        @Override
        public String convertToDatabaseColumn(List<Integer> attribute) {
            try {
                return objectMapper.writeValueAsString(attribute);
            } catch (JsonProcessingException e) {
                throw new IllegalArgumentException("Error al convertir lista de enteros a JSON", e);
            }
        }

        @Override
        public List<Integer> convertToEntityAttribute(String dbData) {
            try {
                return objectMapper.readValue(dbData, new TypeReference<List<Integer>>() {
                });
            } catch (IOException e) {
                throw new IllegalArgumentException("Error al convertir JSON a lista de enteros", e);
            }
        }
    }
}
